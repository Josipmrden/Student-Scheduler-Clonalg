/*    */ package hr.fer.java.zemris.seminar.clonalg.gui;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import javax.swing.JLabel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JFontLabel
/*    */   extends JLabel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 13 */   Font font = new Font("Ariel", 1, 20);
/*    */ 
/*    */   
/*    */   public void setText(String text) {
/* 17 */     super.setText(text);
/* 18 */     setFont(this.font);
/*    */   }
/*    */   
/*    */   public JFontLabel(String text) {
/* 22 */     setText(text);
/*    */   }
/*    */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/gui/JFontLabel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */