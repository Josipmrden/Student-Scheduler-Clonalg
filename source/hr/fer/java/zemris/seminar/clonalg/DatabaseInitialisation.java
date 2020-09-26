/*     */ package hr.fer.java.zemris.seminar.clonalg;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ 
/*     */ public class DatabaseInitialisation
/*     */ {
/*     */   public static Set<GroupClass> initializeGroups(FERDB database) {
/*  13 */     Set<Subject> subjects = new HashSet<>(Arrays.asList(new Subject[] {
/*  14 */             new Subject("VIS", 5), 
/*  15 */             new Subject("SIS", 6), 
/*  16 */             new Subject("UTR", 4), 
/*  17 */             new Subject("INZEKO", 2), 
/*  18 */             new Subject("BAZE", 4)
/*     */           }));
/*  20 */     database.subjects = subjects;
/*     */     
/*  22 */     Set<Room> rooms = new TreeSet<>(Arrays.asList(new Room[] {
/*  23 */             new Room("B1"), 
/*  24 */             new Room("B2"), 
/*  25 */             new Room("B3"), 
/*  26 */             new Room("B4")
/*     */           }));
/*  28 */     database.rooms = rooms;
/*     */ 
/*     */     
/*  31 */     Set<GroupClass> groups = new HashSet<>(Arrays.asList(new GroupClass[] {
/*  32 */             new GroupClass("P1"), 
/*  33 */             new GroupClass("P2"), 
/*  34 */             new GroupClass("P3"), 
/*  35 */             new GroupClass("P4")
/*     */           }));
/*  37 */     database.groups = groups;
/*     */     
/*  39 */     for (GroupClass group : groups) {
/*  40 */       group.subjects = subjects;
/*     */     }
/*     */     
/*  43 */     Set<Professor> professors = new HashSet<>(Arrays.asList(new Professor[] { 
/*  44 */             new Professor("Mario Krnic", "VIS"), 
/*  45 */             new Professor("Tomislav Buric", "VIS"), 
/*  46 */             new Professor("Ilko Brnetic", "VIS"), 
/*  47 */             new Professor("Andrea Aglic Aljinovic", "VIS"), 
/*  48 */             new Professor("Branko Jeren", "SIS"), 
/*  49 */             new Professor("Zvonko Kostanjcar", "SIS"), 
/*  50 */             new Professor("Damir Sersic", "SIS"), 
/*  51 */             new Professor("Ana Sovic", "SIS"), 
/*  52 */             new Professor("Marin Silic", "UTR"), 
/*  53 */             new Professor("Sinisa Srbljic", "UTR"), 
/*  54 */             new Professor("Yao Ming", "UTR"), 
/*  55 */             new Professor("Zoran Kalafatic", "UTR"), 
/*  56 */             new Professor("Igor Mekterovic", "BAZE"), 
/*  57 */             new Professor("Boris Vrdoljak", "BAZE"), 
/*  58 */             new Professor("Mihaela Vranic", "BAZE"), 
/*  59 */             new Professor("Damir Pintar", "BAZE"), 
/*  60 */             new Professor("Dubravko Sabolic", "INZEKO"), 
/*  61 */             new Professor("Željko Štih", "INZEKO"), 
/*  62 */             new Professor("Zeljko Tomsic", "INZEKO"), 
/*  63 */             new Professor("Roman Malaric", "INZEKO") }));
/*     */     
/*  65 */     database.professors = professors;
/*     */     
/*  67 */     ArrayList<GroupClass> groupsList = new ArrayList<>(groups);
/*  68 */     ArrayList<Subject> subjectList = new ArrayList<>(subjects);
/*  69 */     ArrayList<Professor> profList = new ArrayList<>(professors);
/*     */     
/*  71 */     Collections.shuffle(groupsList);
/*  72 */     Collections.shuffle(subjectList);
/*  73 */     Collections.shuffle(profList);
/*     */     
/*  75 */     for (GroupClass group : groupsList) {
/*  76 */       for (Subject subject : subjectList) {
/*  77 */         for (Professor prof : profList) {
/*  78 */           if (subject.name.equals(prof.subjectName) && !prof.teaches) {
/*  79 */             prof.teaches = true;
/*  80 */             group.professors.add(prof);
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*  86 */     for (GroupClass group : groupsList) {
/*  87 */       for (Subject subject : group.subjects) {
/*  88 */         for (Professor prof : group.professors) {
/*  89 */           if (subject.name.equals(prof.subjectName)) {
/*  90 */             prof.group = group;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*  97 */     return groups;
/*     */   }
/*     */   
/*     */   public static Set<Term> initializeTerms(FERDB database) {
/* 101 */     String[] days = {
/* 102 */         "ponedjeljak", 
/* 103 */         "utorak", 
/* 104 */         "srijeda", 
/* 105 */         "cetvrtak", 
/* 106 */         "petak"
/*     */       };
/* 108 */     Set<Term> terms = new TreeSet<>(); byte b; int i; String[] arrayOfString1;
/* 109 */     for (i = (arrayOfString1 = days).length, b = 0; b < i; ) { String day = arrayOfString1[b];
/* 110 */       for (int j = 8; j <= 19; j++)
/* 111 */         terms.add(new Term(j, day)); 
/*     */       b++; }
/*     */     
/* 114 */     database.terms = terms;
/*     */     
/* 116 */     return terms;
/*     */   }
/*     */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/DatabaseInitialisation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */