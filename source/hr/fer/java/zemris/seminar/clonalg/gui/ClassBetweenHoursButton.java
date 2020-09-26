/*    */ package hr.fer.java.zemris.seminar.clonalg.gui;
/*    */ 
/*    */ import java.awt.Font;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClassBetweenHoursButton
/*    */   extends ConstraintButton
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 13 */   Font font = new Font("Ariel", 1, 20);
/* 14 */   JFontTextField startOfDay = new JFontTextField("DEFAULT");
/* 15 */   JFontTextField endOfDay = new JFontTextField("DEFAULT");
/* 16 */   JFontTextField when = new JFontTextField("DEFAULT");
/*    */   
/*    */   public ClassBetweenHoursButton(String name) {
/* 19 */     super(name);
/*    */   }
/*    */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/gui/ClassBetweenHoursButton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */