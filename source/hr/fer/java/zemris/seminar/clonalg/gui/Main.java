/*    */ package hr.fer.java.zemris.seminar.clonalg.gui;
/*    */ 
/*    */ import hr.fer.java.zemris.seminar.clonalg.DatabaseInitialisation;
/*    */ import hr.fer.java.zemris.seminar.clonalg.FERDB;
/*    */ import hr.fer.java.zemris.seminar.clonalg.GroupClass;
/*    */ import hr.fer.java.zemris.seminar.clonalg.Term;
/*    */ import java.awt.BorderLayout;
/*    */ import java.util.Set;
/*    */ import java.util.TreeSet;
/*    */ import javax.swing.JFrame;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.SwingUtilities;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Main
/*    */   extends JFrame
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   
/*    */   public static void main(String[] args) {
/* 24 */     FERDB database = new FERDB();
/*    */     
/* 26 */     Set<GroupClass> groups = new TreeSet<>(DatabaseInitialisation.initializeGroups(database));
/* 27 */     Set<Term> terms = DatabaseInitialisation.initializeTerms(database);
/*    */     
/* 29 */     SwingUtilities.invokeLater(() -> {
/*    */           ClassScheduleCalendar c = new ClassScheduleCalendar(paramSet1, paramSet2, paramFERDB, paramFERDB.getRooms());
/*    */           c.setVisible(true);
/*    */         });
/*    */   }
/*    */   
/*    */   public Main() {
/* 36 */     setTitle("Unesite putove do datoteka");
/* 37 */     setSize(700, 300);
/* 38 */     setDefaultCloseOperation(3);
/* 39 */     setLayout(new BorderLayout());
/*    */     
/* 41 */     JPanel northPanel = new JPanel();
/*    */     
/* 43 */     add(northPanel, "North");
/*    */   }
/*    */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/gui/Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */