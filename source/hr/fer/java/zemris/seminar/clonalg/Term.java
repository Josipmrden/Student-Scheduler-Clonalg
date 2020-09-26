/*    */ package hr.fer.java.zemris.seminar.clonalg;
/*    */ 
/*    */ public class Term
/*    */   implements Comparable<Term> {
/*    */   public static final short MIN_BEGIN_HOUR = 8;
/*    */   public static final short MAX_BEGIN_HOUR = 19;
/*    */   public static final short AFTERNOON = 14;
/*    */   public static final short TERMS_PER_DAY = 12;
/*    */   public static final short NUMBER_OF_DAYS = 7;
/* 10 */   public static int numberOfTerms = 0;
/*    */   
/*    */   int beginHour;
/*    */   
/*    */   String day;
/*    */   int dayNumber;
/*    */   int termId;
/*    */   
/*    */   public Term(int beginHour, String day) {
/* 19 */     this.beginHour = beginHour;
/* 20 */     this.day = day;
/* 21 */     this.dayNumber = determineDayNumber(day);
/* 22 */     this.termId = numberOfTerms++;
/*    */   }
/*    */   
/*    */   private int determineDayNumber(String day2) {
/*    */     String str;
/* 27 */     switch ((str = day2.toLowerCase().trim()).hashCode()) { case -1947308062: if (!str.equals("srijeda")) {
/*    */           break;
/*    */         }
/*    */ 
/*    */ 
/*    */         
/* 33 */         return 3;
/*    */ 
/*    */       
/*    */       case -891533092:
/*    */         if (!str.equals("subota")) {
/*    */           break;
/*    */         }
/* 40 */         return 6;case -834808724: if (!str.equals("utorak")) break;  return 2;case -753396122: if (!str.equals("četvrtak")) break;  return 4;case -678409648: if (!str.equals("cetvrtak")) break;  return 4;case 106557833: if (!str.equals("petak")) break;  return 5;case 632780433: if (!str.equals("ponedjeljak"))
/*    */           break;  return 1;case 843091483: if (!str.equals("nedjelja"))
/* 42 */           break;  return 7; }
/*    */     
/* 44 */     throw new IllegalArgumentException("Wrong day: " + day2);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int compareTo(Term o) {
/* 51 */     if (this.dayNumber < o.dayNumber)
/* 52 */       return -1; 
/* 53 */     if (this.dayNumber > o.dayNumber) {
/* 54 */       return 1;
/*    */     }
/* 56 */     if (this.beginHour < o.beginHour)
/* 57 */       return -1; 
/* 58 */     if (this.beginHour > o.beginHour) {
/* 59 */       return 1;
/*    */     }
/* 61 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 66 */     return String.format("%s %s %d", new Object[] { this.day, Integer.valueOf(this.beginHour), Integer.valueOf(this.termId) });
/*    */   }
/*    */   
/*    */   public int getTermId() {
/* 70 */     return this.termId;
/*    */   }
/*    */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/Term.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */