/*     */ package hr.fer.java.zemris.seminar.clonalg;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EventEvaluator
/*     */ {
/*     */   List<GroupClass> groups;
/*     */   public static final int DEFAULT_NO_SUBJECTS_PER_DAY = 3;
/*     */   public static final int DEFAULT_MAX_HOURS_PER_DAY = 5;
/*     */   public static final int DEFAULT_MIN_HOURS_PER_DAY_SUBJECT = 1;
/*     */   public static final int DEFAULT_MAX_HOURS_PER_DAY_SUBJECT = 3;
/*     */   public static final int DEFAULT_MIN_HOURS_PER_DAY = 1;
/*     */   public static final int DEFAULT_DAY_START = 8;
/*     */   public static final int DEFAULT_DAY_END = 14;
/*     */   public static final double SUBJECT_PER_DAY_MULTIPLICATOR = 0.01D;
/*     */   public static final double CLASS_DURATION_MULTIPLICATOR = 0.005D;
/*     */   public static final double ONE_HOUR_PER_DAY_MULTIPLICATOR = 0.005D;
/*     */   public static final double HOURS_PER_DAY_MULTIPLICATOR = 0.02D;
/*     */   public static final double HOLES_MULTIPLICATOR = 0.004D;
/*     */   public static final double HOLES_IN_SUBJECTS_MULTIPLICATOR = 0.001D;
/*  40 */   double totalMultiplication = 40.0D; int dayStart; int dayEnd; int minHours; int maxHours; int maxNumberOfSubjects; int minHoursPerSubject; int maxHoursPerSubject; ClassStart when; boolean classDuration; boolean noHoles; boolean hoursPerDay; boolean holesInSubjectInDay; boolean numberOfSubjectsPerDay; boolean hoursPerSubject; int numberOfConstraints;
/*     */   
/*     */   public EventEvaluator(Set<GroupClass> groupSet) {
/*  43 */     this.groups = new ArrayList<>(groupSet);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EventEvaluator(GroupClass[] groups, boolean classDuration, boolean noHoles, boolean hoursPerDay, boolean holesInSubjectInDay, boolean numberOfSubjectsPerDay, boolean hoursPerSubject) {
/*  49 */     this.groups = Arrays.asList(groups);
/*  50 */     Collections.sort(this.groups);
/*  51 */     this.classDuration = classDuration;
/*  52 */     this.noHoles = noHoles;
/*  53 */     this.holesInSubjectInDay = holesInSubjectInDay;
/*  54 */     this.hoursPerDay = hoursPerDay;
/*  55 */     this.numberOfSubjectsPerDay = numberOfSubjectsPerDay;
/*  56 */     this.hoursPerSubject = hoursPerSubject;
/*  57 */     this.numberOfConstraints = 0;
/*  58 */     if (classDuration) this.numberOfConstraints++; 
/*  59 */     if (noHoles) this.numberOfConstraints++; 
/*  60 */     if (hoursPerDay) this.numberOfConstraints++; 
/*  61 */     if (holesInSubjectInDay) this.numberOfConstraints++; 
/*  62 */     if (numberOfSubjectsPerDay) this.numberOfConstraints++; 
/*  63 */     if (hoursPerSubject) this.numberOfConstraints++; 
/*  64 */     this.totalMultiplication /= this.numberOfConstraints;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EventEvaluator(GroupClass[] groups, boolean classDuration, String dayStart, String dayEnd, String when, boolean noHoles, boolean hoursPerDay, String minHours, String maxHours, boolean holesInSubjectInDay, boolean numberOfSubjectsPerDay, String maxNumberOfSubjects, boolean hoursPerSubject, String minHoursPerSubject, String maxHoursPerSubject) {
/*  72 */     this(groups, classDuration, noHoles, hoursPerDay, holesInSubjectInDay, numberOfSubjectsPerDay, hoursPerSubject);
/*     */     
/*  74 */     if (when.equalsIgnoreCase("early")) {
/*  75 */       this.when = ClassStart.EARLY;
/*  76 */     } else if (when.equalsIgnoreCase("later")) {
/*  77 */       this.when = ClassStart.LATER;
/*     */     } else {
/*  79 */       this.when = ClassStart.DEFAULT;
/*     */     } 
/*     */     
/*  82 */     if (classDuration) {
/*     */       try {
/*  84 */         this.dayStart = Integer.parseInt(dayStart);
/*  85 */         this.dayEnd = Integer.parseInt(dayEnd);
/*  86 */       } catch (NumberFormatException ex) {
/*  87 */         this.dayStart = 8;
/*  88 */         this.dayEnd = 14;
/*     */       } 
/*     */     }
/*  91 */     if (hoursPerDay) {
/*     */       try {
/*  93 */         this.minHours = Integer.parseInt(maxHours);
/*  94 */         this.maxHours = Integer.parseInt(maxHours);
/*  95 */       } catch (NumberFormatException ex) {
/*  96 */         this.minHours = 1;
/*  97 */         this.maxHours = 5;
/*     */       } 
/*     */     }
/* 100 */     if (numberOfSubjectsPerDay) {
/*     */       try {
/* 102 */         this.maxNumberOfSubjects = Integer.parseInt(maxNumberOfSubjects);
/* 103 */       } catch (NumberFormatException ex) {
/* 104 */         this.maxNumberOfSubjects = 5;
/*     */       } 
/*     */     }
/* 107 */     if (hoursPerSubject) {
/*     */       try {
/* 109 */         this.minHoursPerSubject = Integer.parseInt(minHoursPerSubject);
/* 110 */         this.maxHoursPerSubject = Integer.parseInt(maxHoursPerSubject);
/* 111 */       } catch (NumberFormatException ex) {
/* 112 */         this.minHoursPerSubject = 1;
/* 113 */         this.maxHoursPerSubject = 3;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public double evaluate(Event[][] events) {
/* 119 */     double affinity = 0.0D;
/*     */     
/* 121 */     if (this.numberOfSubjectsPerDay) {
/* 122 */       int penalty = calculatePenaltyForNumberOfSubjectPerDay(events);
/* 123 */       affinity += penalty * 0.01D;
/*     */     } 
/* 125 */     if (this.classDuration) {
/* 126 */       int penalty = calculatePenaltyForClassDuration(events);
/* 127 */       affinity += penalty * 0.005D;
/*     */     } 
/* 129 */     if (this.hoursPerSubject) {
/* 130 */       int penalty = calculatePenaltyForOneHourOfSubjectPerDay(events);
/* 131 */       affinity += penalty * 0.005D;
/*     */     } 
/* 133 */     if (this.hoursPerDay) {
/* 134 */       int penalty = calculatePenaltyForHoursPerDay(events);
/* 135 */       affinity += penalty * 0.02D;
/*     */     } 
/* 137 */     if (this.noHoles) {
/* 138 */       int penalty = calculateHoles(events);
/* 139 */       affinity += penalty * 0.004D;
/*     */     } 
/*     */     
/* 142 */     affinity *= this.totalMultiplication;
/*     */     
/* 144 */     return 1.0D / (1.0D + affinity);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean termInAfternoon(Event[][] events) {
/* 150 */     for (int i = 0; i < events.length; i++) {
/* 151 */       for (int j = 0; j < (events[i]).length; j++) {
/* 152 */         if (i % 12 > 6)
/*     */         {
/*     */           
/* 155 */           if (events[i][j] != null)
/*     */           {
/*     */             
/* 158 */             return true; }  } 
/*     */       } 
/*     */     } 
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int calculateHoles(Event[][] events) {
/* 167 */     int numberOfPenalties = 0;
/*     */     
/* 169 */     for (GroupClass g : this.groups) {
/* 170 */       for (int i = 0; i < 5; i++) {
/* 171 */         int holes = 0;
/* 172 */         List<Integer> hours = new ArrayList<>();
/* 173 */         for (int j = 0; j < 12; j++) {
/* 174 */           for (int k = 0; k < Room.numberOfRooms; k++) {
/* 175 */             Event e = events[i * 12 + j][k];
/* 176 */             if (e != null)
/*     */             {
/*     */               
/* 179 */               if (e.group.equals(g)) {
/*     */ 
/*     */                 
/* 182 */                 hours.add(Integer.valueOf(8 + j)); break;
/*     */               }  } 
/*     */           } 
/*     */         } 
/* 186 */         boolean beginHole = false;
/* 187 */         for (int z = 8; z <= 19; z++) {
/* 188 */           if (hours.contains(Integer.valueOf(z))) {
/* 189 */             if (hours.indexOf(Integer.valueOf(z)) == hours.size() - 1) {
/*     */               break;
/*     */             }
/* 192 */             beginHole = true;
/*     */           
/*     */           }
/* 195 */           else if (beginHole) {
/* 196 */             holes++;
/*     */           } 
/*     */         } 
/* 199 */         numberOfPenalties += holes;
/*     */       } 
/*     */     } 
/* 202 */     return numberOfPenalties;
/*     */   }
/*     */ 
/*     */   
/*     */   private int calculatePenaltyForHoursPerDay(Event[][] events) {
/* 207 */     int numberOfPenalties = 0;
/*     */     
/* 209 */     for (GroupClass g : this.groups) {
/* 210 */       for (int i = 0; i < 5; i++) {
/* 211 */         int numberOfHours = 0;
/* 212 */         for (int j = 0; j < 12; j++) {
/* 213 */           for (int k = 0; k < Room.numberOfRooms; k++) {
/* 214 */             Event e = events[i * 12 + j][k];
/* 215 */             if (e != null)
/*     */             {
/*     */               
/* 218 */               if (e.group.equals(g))
/*     */               {
/*     */                 
/* 221 */                 numberOfHours++; }  } 
/*     */           } 
/*     */         } 
/* 224 */         if (numberOfHours > this.maxHours || numberOfHours < this.minHours) {
/* 225 */           numberOfPenalties++;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 230 */     return numberOfPenalties;
/*     */   }
/*     */ 
/*     */   
/*     */   private int calculatePenaltyForOneHourOfSubjectPerDay(Event[][] events) {
/* 235 */     int numberOfPenalties = 0;
/*     */     
/* 237 */     for (GroupClass g : this.groups) {
/* 238 */       for (int i = 0; i < 5; i++) {
/* 239 */         Map<String, Integer> hoursPerDay = new HashMap<>();
/* 240 */         for (int j = 0; j < 12; j++) {
/* 241 */           for (int k = 0; k < Room.numberOfRooms; k++) {
/* 242 */             Event e = events[i * 12 + j][k];
/* 243 */             if (e != null)
/*     */             {
/*     */               
/* 246 */               if (e.group.equals(g)) {
/*     */ 
/*     */                 
/* 249 */                 if (!hoursPerDay.containsKey(e.subject.name)) {
/* 250 */                   hoursPerDay.put(e.subject.name, Integer.valueOf(0));
/*     */                 }
/* 252 */                 hoursPerDay.put(e.subject.name, Integer.valueOf(((Integer)hoursPerDay.get(e.subject.name)).intValue() + 1));
/*     */               }  } 
/*     */           } 
/*     */         } 
/* 256 */         for (Map.Entry<String, Integer> entry : hoursPerDay.entrySet()) {
/* 257 */           if (((Integer)entry.getValue()).intValue() < this.minHoursPerSubject || ((Integer)entry.getValue()).intValue() > this.maxHoursPerSubject) {
/* 258 */             numberOfPenalties++;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 263 */     return numberOfPenalties;
/*     */   }
/*     */   
/*     */   private int calculatePenaltyForClassDuration(Event[][] events) {
/* 267 */     int numberOfPenalties = 0;
/*     */     
/* 269 */     for (int x = 0; x < 5; x++) {
/* 270 */       int[] terms = new int[this.groups.size()];
/* 271 */       for (int y = 0; y < 12; y++) {
/* 272 */         for (int z = 0; z < Room.numberOfRooms; z++) {
/* 273 */           Event e = events[x * 12 + y][z];
/* 274 */           if (e != null) {
/*     */ 
/*     */             
/* 277 */             if (e.term.beginHour >= this.dayEnd || e.term.beginHour < this.dayStart) {
/* 278 */               numberOfPenalties++;
/*     */             }
/*     */             
/* 281 */             if (this.when != ClassStart.DEFAULT) {
/* 282 */               int index = this.groups.indexOf(e.group);
/*     */               
/* 284 */               if (this.when == ClassStart.EARLY && (
/* 285 */                 terms[index] == 0 || terms[index] > e.term.beginHour)) {
/* 286 */                 terms[index] = e.term.beginHour;
/*     */               }
/*     */ 
/*     */               
/* 290 */               if (this.when == ClassStart.LATER && (
/* 291 */                 terms[index] == 0 || terms[index] < e.term.beginHour)) {
/* 292 */                 terms[index] = e.term.beginHour;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 298 */       if (this.when != ClassStart.DEFAULT) {
/* 299 */         for (int q = 0; q < terms.length; q++) {
/* 300 */           if (this.when == ClassStart.EARLY && terms[q] != 0 && terms[q] > this.dayStart) {
/* 301 */             numberOfPenalties += terms[q] - this.dayStart;
/* 302 */           } else if (this.when == ClassStart.LATER && terms[q] != 0 && terms[q] < this.dayEnd - 1) {
/* 303 */             numberOfPenalties += this.dayEnd - terms[q];
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 320 */     return numberOfPenalties;
/*     */   }
/*     */   
/*     */   private int calculatePenaltyForNumberOfSubjectPerDay(Event[][] events) {
/* 324 */     int numberOfPenalties = 0;
/*     */     
/* 326 */     for (GroupClass g : this.groups) {
/* 327 */       for (int i = 0; i < 5; i++) {
/* 328 */         Set<Subject> subjects = new HashSet<>();
/* 329 */         for (int j = 0; j < 12; j++) {
/* 330 */           for (int k = 0; k < Room.numberOfRooms; k++) {
/* 331 */             Event e = events[i * 12 + j][k];
/* 332 */             if (e != null)
/*     */             {
/*     */               
/* 335 */               if (e.group.equals(g))
/*     */               {
/*     */                 
/* 338 */                 subjects.add(e.subject); }  } 
/*     */           } 
/*     */         } 
/* 341 */         if (subjects.size() > this.maxNumberOfSubjects) {
/* 342 */           numberOfPenalties++;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 347 */     return numberOfPenalties;
/*     */   }
/*     */   
/*     */   private enum ClassStart {
/* 351 */     EARLY, LATER, DEFAULT;
/*     */   }
/*     */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/EventEvaluator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */