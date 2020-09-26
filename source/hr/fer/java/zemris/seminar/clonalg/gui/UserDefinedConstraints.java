/*     */ package hr.fer.java.zemris.seminar.clonalg.gui;
/*     */ 
/*     */ import hr.fer.java.zemris.seminar.clonalg.ClonAlg;
/*     */ import hr.fer.java.zemris.seminar.clonalg.EventEvaluator;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Font;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UserDefinedConstraints
/*     */   extends JFrame
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   ClonAlg clonAlg;
/*     */   ClassScheduleCalendar csc;
/*     */   ConstraintButton noHoles;
/*     */   ConstraintButton holesInSubjectInDay;
/*     */   ClassBetweenHoursButton classDuration;
/*     */   HoursPerDayButton hoursPerDay;
/*     */   HoursPerDayButton hoursPerSubject;
/*     */   SubjectPerDayButton numberOfSubjectsPerDay;
/*     */   JButton go;
/*     */   Thread t;
/*     */   boolean begin;
/*  31 */   Font font = new Font("Ariel", 1, 20);
/*     */ 
/*     */ 
/*     */   
/*     */   public UserDefinedConstraints(ClonAlg clonAlg, ClassScheduleCalendar csc, Thread t, boolean begin) {
/*  36 */     setSize(1200, 500);
/*  37 */     setLocation(20, 20);
/*  38 */     setDefaultCloseOperation(3);
/*  39 */     setTitle("User defined constraints");
/*     */     
/*  41 */     this.begin = begin;
/*  42 */     this.csc = csc;
/*  43 */     this.csc.t = this.t;
/*  44 */     this.clonAlg = clonAlg;
/*  45 */     this.classDuration = new ClassBetweenHoursButton("Class duration, When -> EARLY, LATER, DEFAULT");
/*     */     
/*  47 */     this.noHoles = new ConstraintButton("No holes");
/*  48 */     this.hoursPerDay = new HoursPerDayButton("Number of hours per day");
/*  49 */     this.holesInSubjectInDay = new ConstraintButton("No holes in a subject");
/*  50 */     this.numberOfSubjectsPerDay = new SubjectPerDayButton("Number of subjects per day");
/*  51 */     this.hoursPerSubject = new HoursPerDayButton("Number of hours per subject per day");
/*  52 */     this.go = new JButton("GO CLONALG!");
/*  53 */     setLayout(new BorderLayout());
/*     */     
/*  55 */     JPanel panel = new JPanel(new GridLayout(3, 2));
/*     */     
/*  57 */     JPanel buttonPanel = new JPanel(new BorderLayout());
/*  58 */     JPanel textFieldPanel = new JPanel(new GridLayout(1, 8));
/*  59 */     buttonPanel.add(this.classDuration, "Center");
/*  60 */     buttonPanel.add(textFieldPanel, "South");
/*  61 */     JFontLabel from = new JFontLabel("From: ");
/*  62 */     textFieldPanel.add(from);
/*  63 */     textFieldPanel.add(this.classDuration.startOfDay);
/*  64 */     JFontLabel to = new JFontLabel("To: ");
/*  65 */     textFieldPanel.add(to);
/*  66 */     textFieldPanel.add(this.classDuration.endOfDay);
/*  67 */     textFieldPanel.add(new JFontLabel("When: "));
/*  68 */     textFieldPanel.add(this.classDuration.when);
/*  69 */     panel.add(buttonPanel);
/*     */     
/*  71 */     panel.add(this.noHoles);
/*     */     
/*  73 */     buttonPanel = new JPanel(new BorderLayout());
/*  74 */     textFieldPanel = new JPanel(new GridLayout(1, 4));
/*  75 */     buttonPanel.add(this.hoursPerDay, "Center");
/*  76 */     buttonPanel.add(textFieldPanel, "South");
/*  77 */     JFontLabel min = new JFontLabel("Min: ");
/*  78 */     JFontLabel max = new JFontLabel("Max :");
/*  79 */     textFieldPanel.add(min);
/*  80 */     textFieldPanel.add(this.hoursPerDay.minNumberOfHours);
/*  81 */     textFieldPanel.add(max);
/*  82 */     textFieldPanel.add(this.hoursPerDay.maxNumberOfHours);
/*  83 */     panel.add(buttonPanel);
/*     */     
/*  85 */     panel.add(this.holesInSubjectInDay);
/*     */     
/*  87 */     buttonPanel = new JPanel(new BorderLayout());
/*  88 */     textFieldPanel = new JPanel(new GridLayout(1, 4));
/*  89 */     buttonPanel.add(this.numberOfSubjectsPerDay, "Center");
/*  90 */     buttonPanel.add(textFieldPanel, "South");
/*  91 */     min = new JFontLabel("Min: ");
/*  92 */     max = new JFontLabel("Max: ");
/*  93 */     textFieldPanel.add(max);
/*  94 */     textFieldPanel.add(this.numberOfSubjectsPerDay.max);
/*  95 */     panel.add(buttonPanel);
/*     */ 
/*     */     
/*  98 */     buttonPanel = new JPanel(new BorderLayout());
/*  99 */     textFieldPanel = new JPanel(new GridLayout(1, 4));
/* 100 */     buttonPanel.add(this.hoursPerSubject, "Center");
/* 101 */     buttonPanel.add(textFieldPanel, "South");
/* 102 */     min = new JFontLabel("Min: ");
/* 103 */     max = new JFontLabel("Max :");
/* 104 */     textFieldPanel.add(min);
/* 105 */     textFieldPanel.add(this.hoursPerSubject.minNumberOfHours);
/* 106 */     textFieldPanel.add(max);
/* 107 */     textFieldPanel.add(this.hoursPerSubject.maxNumberOfHours);
/* 108 */     panel.add(buttonPanel);
/*     */ 
/*     */     
/* 111 */     add(panel, "Center");
/* 112 */     add(this.go, "South");
/*     */     
/* 114 */     this.go.addActionListener(e -> {
/*     */           if (!this.classDuration.isSelected() && !this.noHoles.isSelected() && !this.hoursPerDay.isSelected() && !this.holesInSubjectInDay.isSelected() && !this.numberOfSubjectsPerDay.isSelected() && !this.hoursPerSubject.isSelected()) {
/*     */             JOptionPane.showMessageDialog(this, "You must enter at least one constraint, click the toggle buttons and define constraints!", "Error", 0);
/*     */             return;
/*     */           } 
/*     */           this.t = new Thread(());
/*     */           paramClassScheduleCalendar.t = this.t;
/*     */           this.t.start();
/*     */           setVisible(false);
/*     */         });
/*     */   }
/*     */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/gui/UserDefinedConstraints.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */