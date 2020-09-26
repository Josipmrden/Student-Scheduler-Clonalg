/*    */ package hr.fer.java.zemris.seminar.clonalg;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ 
/*    */ public class Event
/*    */ {
/*    */   Term term;
/*    */   Professor professor;
/*    */   Room room;
/*    */   
/*    */   static {
/* 12 */     BY_TERM_HOUR_IN_DAY = ((o1, o2) -> (o1.term.dayNumber < o2.term.dayNumber) ? -1 : ((o1.term.dayNumber > o2.term.dayNumber) ? 1 : ((o1.term.beginHour < o2.term.beginHour) ? -1 : ((o1.term.beginHour > o2.term.beginHour) ? 1 : 0))));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   GroupClass group;
/*    */ 
/*    */   
/*    */   Subject subject;
/*    */ 
/*    */   
/*    */   public static final Comparator<Event> BY_TERM_HOUR_IN_DAY;
/*    */ 
/*    */   
/*    */   public Event(Term term, Room room, GroupClass group, Subject subject) {
/* 27 */     this.term = term;
/* 28 */     this.professor = group.professorForSubject(subject);
/* 29 */     this.room = room;
/* 30 */     this.group = group;
/* 31 */     this.subject = subject;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 37 */     return this.room + " " + this.subject + " " + this.professor;
/*    */   }
/*    */ 
/*    */   
/*    */   public static Event[][] copyOf(Event[][] e) {
/* 42 */     Event[][] e2 = new Event[Term.numberOfTerms][Room.numberOfRooms];
/* 43 */     for (int i = 0; i < e2.length; i++) {
/* 44 */       for (int j = 0; j < (e2[i]).length; j++) {
/* 45 */         if (e[i][j] == null) {
/* 46 */           e2[i][j] = null;
/*    */         } else {
/*    */           
/* 49 */           e2[i][j] = new Event((e[i][j]).term, (e[i][j]).room, (e[i][j]).group, (e[i][j]).subject);
/*    */         } 
/*    */       } 
/* 52 */     }  return e2;
/*    */   }
/*    */   
/*    */   public Term getTerm() {
/* 56 */     return this.term;
/*    */   }
/*    */   
/*    */   public Professor getProfessor() {
/* 60 */     return this.professor;
/*    */   }
/*    */   
/*    */   public Room getRoom() {
/* 64 */     return this.room;
/*    */   }
/*    */   
/*    */   public GroupClass getGroup() {
/* 68 */     return this.group;
/*    */   }
/*    */   
/*    */   public Subject getSubject() {
/* 72 */     return this.subject;
/*    */   }
/*    */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/Event.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */