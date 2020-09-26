/*    */ package hr.fer.java.zemris.seminar.clonalg;
/*    */ 
/*    */ public class Subject
/*    */   implements Comparable<Subject> {
/*    */   String name;
/*    */   int hoursPerWeek;
/*    */   
/*    */   public Subject(String name, int hoursPerWeek) {
/*  9 */     this.name = name;
/* 10 */     this.hoursPerWeek = hoursPerWeek;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Subject o) {
/* 15 */     return this.name.compareTo(o.name);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 25 */     int prime = 31;
/* 26 */     int result = 1;
/* 27 */     result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
/* 28 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 33 */     if (this == obj)
/* 34 */       return true; 
/* 35 */     if (obj == null)
/* 36 */       return false; 
/* 37 */     if (!(obj instanceof Subject))
/* 38 */       return false; 
/* 39 */     Subject other = (Subject)obj;
/* 40 */     if (this.name == null) {
/* 41 */       if (other.name != null)
/* 42 */         return false; 
/* 43 */     } else if (!this.name.equals(other.name)) {
/* 44 */       return false;
/* 45 */     }  return true;
/*    */   }
/*    */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/Subject.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */