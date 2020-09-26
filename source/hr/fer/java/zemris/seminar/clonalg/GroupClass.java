/*    */ package hr.fer.java.zemris.seminar.clonalg;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ 
/*    */ public class GroupClass
/*    */   implements Comparable<GroupClass> {
/*    */   String id;
/* 11 */   Set<Subject> subjects = new TreeSet<>();
/* 12 */   Set<Professor> professors = new TreeSet<>();
/*    */   Color color;
/* 14 */   static Random rand = new Random();
/*    */   
/*    */   public GroupClass(String id) {
/* 17 */     this.id = id;
/* 18 */     this.color = new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int compareTo(GroupClass o) {
/* 24 */     return this.id.compareTo(o.id);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 29 */     return String.format("%s", new Object[] { this.id });
/*    */   }
/*    */   
/*    */   public Professor professorForSubject(Subject subject) {
/* 33 */     TreeSet<Professor> iteratingSet = (TreeSet<Professor>)this.professors;
/* 34 */     for (Professor p : iteratingSet) {
/* 35 */       if (p.subjectName.equals(subject.name)) {
/* 36 */         return p;
/*    */       }
/*    */     } 
/* 39 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 44 */     int prime = 31;
/* 45 */     int result = 1;
/* 46 */     result = 31 * result + ((this.id == null) ? 0 : this.id.hashCode());
/* 47 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 52 */     if (this == obj)
/* 53 */       return true; 
/* 54 */     if (obj == null)
/* 55 */       return false; 
/* 56 */     if (!(obj instanceof GroupClass))
/* 57 */       return false; 
/* 58 */     GroupClass other = (GroupClass)obj;
/* 59 */     if (this.id == null) {
/* 60 */       if (other.id != null)
/* 61 */         return false; 
/* 62 */     } else if (!this.id.equals(other.id)) {
/* 63 */       return false;
/* 64 */     }  return true;
/*    */   }
/*    */   
/*    */   public String getId() {
/* 68 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(String id) {
/* 72 */     this.id = id;
/*    */   }
/*    */   
/*    */   public Set<Subject> getSubjects() {
/* 76 */     return this.subjects;
/*    */   }
/*    */   
/*    */   public void setSubjects(Set<Subject> subjects) {
/* 80 */     this.subjects = subjects;
/*    */   }
/*    */   
/*    */   public Set<Professor> getProfessors() {
/* 84 */     return this.professors;
/*    */   }
/*    */   
/*    */   public void setProfessors(Set<Professor> professors) {
/* 88 */     this.professors = professors;
/*    */   }
/*    */   
/*    */   public Color getColor() {
/* 92 */     return this.color;
/*    */   }
/*    */   
/*    */   public void setColor(Color color) {
/* 96 */     this.color = color;
/*    */   }
/*    */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/GroupClass.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */