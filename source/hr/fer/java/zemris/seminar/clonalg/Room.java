/*    */ package hr.fer.java.zemris.seminar.clonalg;
/*    */ 
/*    */ public class Room
/*    */   implements Comparable<Room> {
/*    */   String name;
/*    */   int roomId;
/*  7 */   static int numberOfRooms = 0;
/*    */ 
/*    */   
/*    */   public Room(String name) {
/* 11 */     this.name = name;
/* 12 */     this.roomId = numberOfRooms++;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Room o) {
/* 17 */     if (this.roomId < o.roomId)
/* 18 */       return -1; 
/* 19 */     if (this.roomId > o.roomId) {
/* 20 */       return 1;
/*    */     }
/* 22 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 32 */     int prime = 31;
/* 33 */     int result = 1;
/* 34 */     result = 31 * result + ((this.name == null) ? 0 : this.name.hashCode());
/* 35 */     return result;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 40 */     if (this == obj)
/* 41 */       return true; 
/* 42 */     if (obj == null)
/* 43 */       return false; 
/* 44 */     if (!(obj instanceof Room))
/* 45 */       return false; 
/* 46 */     Room other = (Room)obj;
/* 47 */     if (this.name == null) {
/* 48 */       if (other.name != null)
/* 49 */         return false; 
/* 50 */     } else if (!this.name.equals(other.name)) {
/* 51 */       return false;
/* 52 */     }  return true;
/*    */   }
/*    */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/Room.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */