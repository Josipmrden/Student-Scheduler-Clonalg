/*    */ package hr.fer.java.zemris.seminar.clonalg;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ 
/*    */ public class Professor
/*    */   implements Comparable<Professor> {
/*    */   String name;
/* 10 */   Set<Term> terms = new TreeSet<>();
/*    */   String subjectName;
/*    */   GroupClass group;
/*    */   
/*    */   static {
/* 15 */     BY_SUBJECT = ((o1, o2) -> o1.subjectName.compareTo(o2.subjectName));
/*    */ 
/*    */ 
/*    */     
/* 19 */     BY_NAME = ((o1, o2) -> o1.name.compareTo(o2.subjectName));
/*    */   }
/*    */   
/*    */   boolean teaches;
/*    */   
/*    */   public Professor(String name, String subjectName) {
/* 25 */     this.name = name;
/* 26 */     this.subjectName = subjectName;
/*    */   }
/*    */   
/*    */   public static final Comparator<Professor> BY_SUBJECT;
/*    */   
/*    */   public int compareTo(Professor o) {
/* 32 */     int subject = this.subjectName.compareTo(o.subjectName);
/* 33 */     if (subject != 0) {
/* 34 */       return subject;
/*    */     }
/* 36 */     return this.name.compareTo(o.name);
/*    */   }
/*    */   public static final Comparator<Professor> BY_NAME;
/*    */   
/*    */   public String toString() {
/* 41 */     String[] names = this.name.split(" ");
/* 42 */     String value = "";
/* 43 */     for (int i = 0; i < names.length; i++) {
/* 44 */       if (i < names.length - 1) {
/* 45 */         value = String.valueOf(value) + names[i].charAt(0) + ".";
/*    */       } else {
/*    */         
/* 48 */         value = String.valueOf(value) + " " + names[i];
/*    */       } 
/* 50 */     }  return String.format("%s", new Object[] { value });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 56 */     int prime = 31;
/* 57 */     int result = 1;
/* 58 */     result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
/* 59 */     return result;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 65 */     if (this == obj)
/* 66 */       return true; 
/* 67 */     if (obj == null)
/* 68 */       return false; 
/* 69 */     if (!(obj instanceof Professor))
/* 70 */       return false; 
/* 71 */     Professor other = (Professor)obj;
/* 72 */     if (this.name == null) {
/* 73 */       if (other.name != null)
/* 74 */         return false; 
/* 75 */     } else if (!this.name.equals(other.name)) {
/* 76 */       return false;
/* 77 */     }  return true;
/*    */   }
/*    */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/Professor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */