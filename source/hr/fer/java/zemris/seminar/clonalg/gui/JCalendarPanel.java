/*    */ package hr.fer.java.zemris.seminar.clonalg.gui;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class JCalendarPanel
/*    */   extends JPanel
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   JLabel label;
/*    */   
/*    */   public JCalendarPanel() {
/* 19 */     this.label = new JLabel();
/* 20 */     this.label.setFont(new Font("Ariel", 1, 20));
/* 21 */     add(this.label);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setText(String text) {
/* 29 */     this.label.setText(text);
/*    */   }
/*    */   
/*    */   public String getText() {
/* 33 */     return this.label.getText();
/*    */   }
/*    */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/gui/JCalendarPanel.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */