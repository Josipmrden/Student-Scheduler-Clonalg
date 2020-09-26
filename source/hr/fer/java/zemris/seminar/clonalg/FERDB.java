/*    */ package hr.fer.java.zemris.seminar.clonalg;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class FERDB
/*    */ {
/*    */   Set<Subject> subjects;
/*    */   Set<Term> terms;
/*    */   Set<Room> rooms;
/*    */   Set<GroupClass> groups;
/*    */   Set<Professor> professors;
/* 14 */   public static List<String> days = Arrays.asList(new String[] { "ponedjeljak", "utorak", "srijeda", "četvrtak", "petak", "subota", "nedjelja" });
/*    */   
/*    */   public Set<Room> getRooms() {
/* 17 */     return this.rooms;
/*    */   }
/*    */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/FERDB.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */