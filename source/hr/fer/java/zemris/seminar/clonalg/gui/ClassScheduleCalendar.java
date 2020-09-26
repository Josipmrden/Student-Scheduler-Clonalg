/*     */ package hr.fer.java.zemris.seminar.clonalg.gui;
/*     */ 
/*     */ import hr.fer.java.zemris.seminar.clonalg.ClassObserver;
/*     */ import hr.fer.java.zemris.seminar.clonalg.ClonAlg;
/*     */ import hr.fer.java.zemris.seminar.clonalg.Event;
/*     */ import hr.fer.java.zemris.seminar.clonalg.FERDB;
/*     */ import hr.fer.java.zemris.seminar.clonalg.GroupClass;
/*     */ import hr.fer.java.zemris.seminar.clonalg.Room;
/*     */ import hr.fer.java.zemris.seminar.clonalg.ScheduleSolution;
/*     */ import hr.fer.java.zemris.seminar.clonalg.Term;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.SwingUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ClassScheduleCalendar
/*     */   extends JFrame
/*     */   implements ClassObserver
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  37 */   JCalendarPanel[][] fields = new JCalendarPanel[ROWS][COLUMNS];
/*  38 */   static int ROWS = 13;
/*  39 */   static int COLUMNS = 8;
/*     */   
/*     */   GroupClass groupToWatch;
/*     */   
/*     */   boolean allGroups;
/*     */   
/*     */   JButton start;
/*     */   
/*     */   JButton stop;
/*     */   
/*     */   JButton continueButton;
/*     */   
/*     */   List<GroupClass> groups;
/*     */   
/*     */   public ClassScheduleCalendar(Set<GroupClass> groups, Set<Term> terms, FERDB database, Set<Room> rooms) {
/*  54 */     this.groups = new ArrayList<>(groups);
/*  55 */     this.clonAlg = new ClonAlg(groups, terms, rooms);
/*  56 */     Collections.sort(this.groups);
/*  57 */     this.groupToWatch = this.groups.get(0);
/*  58 */     this.terms = new ArrayList<>(terms);
/*  59 */     this.database = database;
/*  60 */     setTitle("Raspored sati uporabom klonske selekcije");
/*  61 */     setDefaultCloseOperation(3);
/*  62 */     initGUI();
/*  63 */     setSize(1500, 900);
/*     */     
/*  65 */     this.start.addActionListener(e -> {
/*     */           this.clonAlg.setIteration(1);
/*     */           
/*     */           this.stop.setEnabled(true);
/*     */           this.continueButton.setEnabled(false);
/*     */           clearAll();
/*     */           this.udc = new UserDefinedConstraints(this.clonAlg, this, this.t, true);
/*     */           this.udc.setVisible(true);
/*     */         });
/*  74 */     this.stop.addActionListener(e -> {
/*     */           this.stop.setEnabled(false);
/*     */           this.continueButton.setEnabled(true);
/*     */           this.clonAlg.finish();
/*     */           this.t.stop();
/*     */           this.udc.dispose();
/*     */         });
/*  81 */     this.continueButton.addActionListener(e -> {
/*     */           this.continueButton.setEnabled(false);
/*     */           this.stop.setEnabled(true);
/*     */           if (this.udc != null) {
/*     */             this.udc.setVisible(true);
/*     */             this.udc.begin = false;
/*     */           } else {
/*     */             this.udc = new UserDefinedConstraints(this.clonAlg, this, this.t, true);
/*     */           } 
/*     */         });
/*     */   }
/*     */   List<Term> terms; FERDB database; Event[][] lastSolution; Thread t; JLabel progress; ClonAlg clonAlg; UserDefinedConstraints udc;
/*     */   private void clearAll() {
/*  94 */     for (int i = 1; i < this.fields.length; i++) {
/*  95 */       for (int j = 1; j < (this.fields[i]).length; j++) {
/*  96 */         this.fields[i][j].setText("");
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void initGUI() {
/* 103 */     JPanel mainPanel = new JPanel(new BorderLayout());
/* 104 */     add(mainPanel, "Center");
/* 105 */     JPanel labelPanel = new JPanel();
/* 106 */     labelPanel.setSize((int)mainPanel.getSize().getWidth(), 500);
/* 107 */     this.progress = new JLabel();
/* 108 */     this.progress.setFont(new Font("Ariel", 1, 30));
/* 109 */     this.progress.setHorizontalAlignment(0);
/* 110 */     labelPanel.add(this.progress);
/* 111 */     add(labelPanel, "South");
/* 112 */     mainPanel.setLayout(new GridLayout(ROWS, COLUMNS));
/*     */ 
/*     */     
/* 115 */     for (int i = 0; i < this.fields.length; i++) {
/* 116 */       for (int j = 0; j < (this.fields[i]).length; j++) {
/*     */         
/* 118 */         this.fields[i][j] = new JCalendarPanel();
/* 119 */         JCalendarPanel p = this.fields[i][j];
/* 120 */         p.setBorder(BorderFactory.createLineBorder(Color.BLACK));
/*     */         
/* 122 */         if (i == 0 && j != 0) {
/* 123 */           p.setText(FERDB.days.get(j - 1));
/* 124 */           p.setBackground(Color.YELLOW);
/* 125 */           mainPanel.add(p);
/*     */         } 
/*     */         
/* 128 */         if (j == 0 && i != 0) {
/* 129 */           String value = String.format("%d:00 - %d:00", new Object[] { Integer.valueOf(i + 7), Integer.valueOf(i + 8) });
/* 130 */           p.setText(value);
/* 131 */           p.setBackground(Color.ORANGE);
/*     */         } 
/*     */         
/* 134 */         if (i == 0 && j == 0) {
/* 135 */           this.start = new JButton("Start");
/* 136 */           p.setLayout(new BorderLayout());
/* 137 */           JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
/* 138 */           buttonPanel.add(this.start);
/* 139 */           this.stop = new JButton("Stop");
/* 140 */           this.stop.setEnabled(false);
/* 141 */           buttonPanel.add(this.stop);
/* 142 */           this.continueButton = new JButton("Cont");
/* 143 */           this.continueButton.setEnabled(false);
/* 144 */           buttonPanel.add(this.continueButton);
/* 145 */           p.add(buttonPanel, "Center");
/* 146 */           JPanel panel = new JPanel(new GridLayout(1, this.groups.size()));
/*     */           
/* 148 */           ButtonGroup buttonGroups = new ButtonGroup();
/* 149 */           for (int k = 0; k < this.groups.size(); k++) {
/* 150 */             JRadioButton jb = new JRadioButton(((GroupClass)this.groups.get(k)).getId());
/* 151 */             if (k == 0) {
/* 152 */               jb.setSelected(true);
/*     */             }
/* 154 */             buttonGroups.add(jb);
/* 155 */             jb.setActionCommand(((GroupClass)this.groups.get(k)).getId());
/* 156 */             jb.addActionListener(e -> {
/*     */                   this.allGroups = false;
/*     */                   for (int z = 0; z < this.groups.size(); z++) {
/*     */                     if (((GroupClass)this.groups.get(z)).getId().equals(paramJRadioButton.getActionCommand())) {
/*     */                       this.groupToWatch = this.groups.get(z);
/*     */                       break;
/*     */                     } 
/*     */                   } 
/*     */                   if (this.lastSolution != null) {
/*     */                     modify(this.lastSolution);
/*     */                   }
/*     */                 });
/* 168 */             panel.add(jb);
/*     */           } 
/* 170 */           p.add(panel, "South");
/*     */         } 
/* 172 */         mainPanel.add(p);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void valueChanged(ScheduleSolution solution) {
/* 182 */     SwingUtilities.invokeLater(() -> modify(paramScheduleSolution.getSolution()));
/*     */ 
/*     */ 
/*     */     
/* 186 */     this.lastSolution = solution.getSolution();
/*     */   }
/*     */   
/*     */   private void modify(Event[][] solution) {
/* 190 */     clearAll();
/* 191 */     for (int i = 0; i < solution.length; i++) {
/* 192 */       for (int j = 0; j < (solution[i]).length; j++) {
/* 193 */         if (solution[i][j] != null && solution[i][j].getGroup().getId().equals(this.groupToWatch.getId())) {
/* 194 */           int term = solution[i][j].getTerm().getTermId();
/* 195 */           int row = term % 12;
/* 196 */           int column = term / 12;
/* 197 */           JCalendarPanel p = this.fields[row + 1][column + 1];
/* 198 */           p.setText(solution[i][j].toString());
/*     */         } 
/*     */       } 
/*     */     } 
/* 202 */     if (this.t.isAlive()) {
/* 203 */       this.progress.setText("Broj iteracija: " + this.clonAlg.getIteration() + " Fitness: " + this.clonAlg.getBest().getAffinity());
/*     */     } else {
/* 205 */       this.progress.setText("Algoritam je završio! Rješenje dobiveno u " + this.clonAlg.getIteration() + " iteracija. Dobiveni fitness bio je " + this.clonAlg.getBest().getAffinity());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/gui/ClassScheduleCalendar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */