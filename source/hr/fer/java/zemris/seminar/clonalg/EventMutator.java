/*     */ package hr.fer.java.zemris.seminar.clonalg;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ public class EventMutator
/*     */ {
/*  11 */   static Random rand = new Random();
/*     */ 
/*     */   
/*     */   public static void mutate(ScheduleSolution s, EventEvaluator evaluator, List<Term> terms, double affinity) {
/*  15 */     applyRandom(s, evaluator, terms, affinity);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void myMutation(ScheduleSolution s, EventEvaluator evaluator, List<Term> terms, double affinity) {
/*  22 */     Event[][] e = s.solution;
/*  23 */     Event e1 = null;
/*  24 */     int randX1 = 0, randY1 = 0;
/*     */     
/*  26 */     while (e1 == null) {
/*  27 */       randX1 = rand.nextInt(e.length);
/*  28 */       randY1 = rand.nextInt((e[0]).length);
/*  29 */       if (randX1 % Term.numberOfTerms <= 7) {
/*     */         continue;
/*     */       }
/*  32 */       e1 = e[randX1][randY1];
/*     */     } 
/*     */     
/*  35 */     int randX2 = 0, randY2 = 0;
/*     */     
/*     */     while (true) {
/*  38 */       int tries = 0;
/*  39 */       while (tries < 15) {
/*  40 */         randX2 = rand.nextInt(e.length);
/*  41 */         randY2 = rand.nextInt((e[randX2]).length);
/*  42 */         if (randX2 % Term.numberOfTerms < 7) {
/*     */           break;
/*     */         }
/*  45 */         tries++;
/*     */       } 
/*     */       
/*  48 */       while (randX2 - 1 >= 0 && (randX2 - 1) % Term.numberOfTerms < 7) {
/*  49 */         randX2--;
/*     */       }
/*     */       
/*  52 */       if (randX2 % Term.numberOfTerms <= 7) {
/*  53 */         randY2 = getInOtherRoom(randX2, randY2, e);
/*     */       }
/*     */       
/*  56 */       if (!checkHardConstraints(e, randX1, randY1, randX2, randY2)) {
/*     */         continue;
/*     */       }
/*     */       
/*     */       break;
/*     */     } 
/*  62 */     switchEvents(e, randX1, randY1, randX2, randY2, terms);
/*     */     
/*  64 */     if (evaluator.evaluate(e) < affinity) {
/*  65 */       switchEvents(e, randX1, randY1, randX2, randY2, terms);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void switchEvents(Event[][] e, int x1, int y1, int x2, int y2, List<Term> terms) {
/*  71 */     Event temp = e[x1][y1];
/*  72 */     e[x1][y1] = e[x2][y2];
/*  73 */     e[x2][y2] = temp;
/*  74 */     refreshTerm(e[x1][y1], x1, terms);
/*  75 */     refreshTerm(e[x2][y2], x2, terms);
/*     */   }
/*     */   
/*     */   private static void refreshTerm(Event event, int row, List<Term> terms) {
/*  79 */     if (event == null)
/*     */       return; 
/*  81 */     event.term = terms.get(row);
/*     */   }
/*     */   
/*     */   private static int getInOtherRoom(int randX2, int randY2, Event[][] e) {
/*  85 */     for (int i = 0; i < (e[randX2]).length; i++) {
/*  86 */       if (e[randX2][i] == null) {
/*  87 */         return i;
/*     */       }
/*     */     } 
/*  90 */     return randY2;
/*     */   }
/*     */   
/*     */   private static void applyRandom(ScheduleSolution s, EventEvaluator evaluator, List<Term> terms, double affinity) {
/*  94 */     Event[][] e = s.solution;
/*  95 */     int X1 = 0, Y1 = 0, X2 = 0, Y2 = 0;
/*     */     while (true) {
/*  97 */       X1 = rand.nextInt(e.length);
/*  98 */       Y1 = rand.nextInt((e[X1]).length);
/*  99 */       X2 = rand.nextInt(e.length);
/* 100 */       Y2 = rand.nextInt((e[X2]).length);
/*     */       
/* 102 */       if (!checkHardConstraints(e, X1, Y1, X2, Y2)) {
/*     */         continue;
/*     */       }
/*     */       
/*     */       break;
/*     */     } 
/*     */     
/* 109 */     switchEvents(e, X1, Y1, X2, Y2, terms);
/*     */   }
/*     */   
/*     */   private static boolean checkHardConstraints(Event[][] e, int randX1, int randY1, int randX2, int randY2) {
/* 113 */     for (int i = 0; i < Room.numberOfRooms; i++) {
/* 114 */       if (e[randX2][i] != null && e[randX1][randY1] != null && (e[randX1][randY1]).group.equals((e[randX2][i]).group)) {
/* 115 */         return false;
/*     */       }
/* 117 */       if (e[randX1][i] != null && e[randX2][randY2] != null && 
/* 118 */         (e[randX2][randY2]).group.equals((e[randX1][i]).group)) {
/* 119 */         return false;
/*     */       }
/*     */     } 
/* 122 */     return true;
/*     */   }
/*     */   
/*     */   public static void group(ScheduleSolution best, EventEvaluator evaluator, List<Term> terms) {
/* 126 */     Event[][] e = best.solution;
/* 127 */     List<GroupClass> groups = new ArrayList<>(extractGroups(e));
/*     */     
/* 129 */     for (GroupClass g : groups) {
/* 130 */       for (int i = 0; i < 5; i++) {
/* 131 */         List<Event> eventList = new ArrayList<>();
/* 132 */         for (int j = 0; j < 12; j++) {
/* 133 */           for (int k = 0; k < (e[i]).length; k++) {
/* 134 */             Event e1 = e[i * 12 + j][k];
/* 135 */             if (e1 != null)
/*     */             {
/*     */               
/* 138 */               if (e1.getGroup().equals(g))
/*     */               {
/*     */                 
/* 141 */                 eventList.add(e1); }  } 
/*     */           } 
/*     */         } 
/* 144 */         groupTerms(eventList);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void groupTerms(List<Event> list) {
/* 150 */     List<Subject> subjects = new ArrayList<>();
/* 151 */     for (Event e : list) {
/* 152 */       subjects.add(e.subject);
/*     */     }
/* 154 */     boolean change = true;
/* 155 */     int size = list.size();
/* 156 */     for (int i = 0; change; i++) {
/* 157 */       change = false;
/* 158 */       for (int j = 0; j < size - 1 - i; j++) {
/* 159 */         Event e2 = list.get(j + 1);
/* 160 */         Event e1 = list.get(j);
/* 161 */         if (subjects.indexOf(e2.subject) < subjects.indexOf(e1.subject)) {
/*     */           
/* 163 */           Room tempr = e1.room;
/* 164 */           Subject temps = e1.subject;
/* 165 */           Professor tempp = e1.professor;
/* 166 */           e1.room = e2.room;
/* 167 */           e1.professor = e2.professor;
/* 168 */           e1.subject = e2.subject;
/* 169 */           e2.room = tempr;
/* 170 */           e2.professor = tempp;
/* 171 */           e2.subject = temps;
/* 172 */           change = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Set<GroupClass> extractGroups(Event[][] e) {
/* 179 */     Set<GroupClass> groups = new TreeSet<>();
/* 180 */     for (int i = 0; i < e.length; i++) {
/* 181 */       for (int j = 0; j < (e[i]).length; j++) {
/* 182 */         Event e1 = e[i][j];
/* 183 */         if (e1 != null)
/*     */         {
/*     */           
/* 186 */           groups.add(e1.getGroup()); } 
/*     */       } 
/*     */     } 
/* 189 */     return groups;
/*     */   }
/*     */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/EventMutator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */