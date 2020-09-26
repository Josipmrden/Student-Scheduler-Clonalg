/*    */ package hr.fer.java.zemris.seminar.clonalg;
/*    */ 
/*    */ 
/*    */ public class ScheduleSolution
/*    */   implements Comparable<ScheduleSolution>
/*    */ {
/*    */   Event[][] solution;
/*    */   double affinity;
/*    */   
/*    */   public ScheduleSolution(Event[][] solution) {
/* 11 */     this.solution = solution;
/*    */   }
/*    */   
/*    */   public ScheduleSolution(Event[][] solution, double affinity) {
/* 15 */     this.solution = solution;
/* 16 */     this.affinity = affinity;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(ScheduleSolution o) {
/* 21 */     if (this.affinity > o.affinity) {
/* 22 */       return -1;
/*    */     }
/* 24 */     if (this.affinity < o.affinity) {
/* 25 */       return 1;
/*    */     }
/* 27 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 32 */     String value = "";
/* 33 */     for (int i = 0; i < this.solution.length; i++) {
/* 34 */       for (int j = 0; j < (this.solution[i]).length; j++) {
/* 35 */         value = String.valueOf(value) + ((this.solution[i][j] == null) ? "" : (this.solution[i][j] + " |"));
/* 36 */         value = String.valueOf(value) + ((j == (this.solution[i]).length - 1) ? "\n" : "");
/*    */       } 
/*    */     } 
/* 39 */     return value;
/*    */   }
/*    */ 
/*    */   
/*    */   public Event[][] getSolution() {
/* 44 */     return this.solution;
/*    */   }
/*    */   
/*    */   public double getAffinity() {
/* 48 */     return this.affinity;
/*    */   }
/*    */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/ScheduleSolution.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */