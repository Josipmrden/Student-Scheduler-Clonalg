/*    */ package hr.fer.java.zemris.seminar.clonalg.gui;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import javax.swing.JTextField;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JFontTextField
/*    */   extends JTextField
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/* 15 */   Font font = new Font("Ariel", 1, 20);
/*    */   
/*    */   public JFontTextField(String text) {
/* 18 */     setText(text);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setText(String text) {
/* 23 */     super.setText(text);
/* 24 */     setFont(this.font);
/*    */   }
/*    */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/gui/JFontTextField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */