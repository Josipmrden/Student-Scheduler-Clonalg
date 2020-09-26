/*     */ package hr.fer.java.zemris.seminar.clonalg;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.Callable;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.Future;
/*     */ 
/*     */ 
/*     */ public class ClonAlg
/*     */ {
/*  17 */   static FERDB database = new FERDB();
/*     */   
/*  19 */   int paramN = 100;
/*  20 */   int paramD = 10;
/*  21 */   int paramBeta = 10;
/*     */   int clonedPopulationSize;
/*  23 */   int printParameter = 1;
/*  24 */   int maxMutations = 10;
/*  25 */   volatile int iteration = 1;
/*  26 */   int offsetMutations = 0;
/*  27 */   int iterationLimit = Integer.MAX_VALUE;
/*     */   
/*     */   GroupClass[] groups;
/*     */   
/*     */   List<Term> terms;
/*     */   
/*     */   Room[] rooms;
/*     */   public EventEvaluator evaluator;
/*  35 */   Random rand = new Random();
/*     */   
/*     */   ScheduleSolution best;
/*     */   
/*     */   ScheduleSolution[] population;
/*     */   
/*     */   ScheduleSolution[] clonedPopulation;
/*     */   int[] clonedPopulationRanks;
/*     */   ExecutorService pool;
/*  44 */   List<ClassObserver> observers = new ArrayList<>();
/*     */   
/*     */   public void addObserver(ClassObserver o) {
/*  47 */     this.observers.add(o);
/*     */   }
/*     */   
/*     */   public ClonAlg(Set<GroupClass> groups, Set<Term> terms, Set<Room> rooms) {
/*  51 */     this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
/*  52 */     for (int i = 1; i <= this.paramN; i++) {
/*  53 */       this.clonedPopulationSize += (int)((this.paramBeta * this.paramN) / i + 0.5D);
/*     */     }
/*     */     
/*  56 */     this.groups = new GroupClass[groups.size()];
/*  57 */     groups.toArray(this.groups);
/*  58 */     this.terms = new ArrayList<>(terms);
/*  59 */     this.rooms = new Room[rooms.size()];
/*     */ 
/*     */     
/*  62 */     rooms.toArray(this.rooms);
/*  63 */     this.population = new ScheduleSolution[this.paramN];
/*  64 */     this.clonedPopulation = new ScheduleSolution[this.clonedPopulationSize];
/*  65 */     this.clonedPopulationRanks = new int[this.clonedPopulationSize];
/*     */   }
/*     */   
/*     */   private void initialize() {
/*  69 */     for (int i = 0; i < this.paramN; i++) {
/*  70 */       ScheduleSolution s = randomGenerateSolution();
/*  71 */       this.population[i] = s;
/*     */     } 
/*  73 */     this.best = this.population[0];
/*     */   }
/*     */ 
/*     */   
/*     */   private ScheduleSolution randomGenerateSolution() {
/*  78 */     Event[][] event = new Event[Term.numberOfTerms][Room.numberOfRooms];
/*  79 */     for (int i = 0; i < this.groups.length; i++) {
/*  80 */       for (Subject s : (this.groups[i]).subjects) {
/*  81 */         for (int j = 0; j < s.hoursPerWeek; j++) {
/*     */           
/*  83 */           int randomTerm = this.rand.nextInt(Term.numberOfTerms);
/*  84 */           int randomRoom = this.rand.nextInt(Room.numberOfRooms);
/*     */           
/*  86 */           if (event[randomTerm][randomRoom] != null) {
/*  87 */             j--;
/*     */           }
/*     */           else {
/*     */             
/*  91 */             int k = 0;
/*  92 */             boolean repeat = false;
/*  93 */             while (k < this.rooms.length) {
/*  94 */               if (event[randomTerm][k] != null && (event[randomTerm][k]).group.id.equals((this.groups[i]).id)) {
/*  95 */                 repeat = true;
/*     */                 break;
/*     */               } 
/*  98 */               k++;
/*     */             } 
/* 100 */             if (repeat) {
/* 101 */               repeat = false;
/* 102 */               j--;
/*     */             }
/*     */             else {
/*     */               
/* 106 */               event[randomTerm][randomRoom] = new Event(this.terms.get(randomTerm), this.rooms[randomRoom], this.groups[i], s);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 111 */     }  return new ScheduleSolution(event);
/*     */   }
/*     */ 
/*     */   
/*     */   public void go() {
/* 116 */     initialize();
/* 117 */     notifyObservers();
/* 118 */     iterate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void iterate() {
/* 123 */     boolean begin = true;
/* 124 */     double affinity = 0.0D;
/* 125 */     int generationOffset = 0;
/* 126 */     while ((this.iteration < this.iterationLimit && this.best.affinity != 1.0D) || begin) {
/* 127 */       begin = false;
/* 128 */       parallelEvaluation(this.population);
/* 129 */       cloning();
/* 130 */       parallelHyperMutation();
/* 131 */       parallelEvaluation(this.clonedPopulation);
/* 132 */       select();
/* 133 */       birthAndReplace();
/* 134 */       this.iteration++;
/* 135 */       this.best = this.population[0];
/* 136 */       if (affinity != this.best.affinity) {
/* 137 */         generationOffset = 0;
/* 138 */         affinity = this.best.affinity;
/*     */       } else {
/* 140 */         generationOffset++;
/*     */       } 
/* 142 */       if (generationOffset >= 50) {
/* 143 */         this.offsetMutations = this.rand.nextInt(11);
/*     */       }
/* 145 */       if (this.iteration % this.printParameter == 0) {
/* 146 */         notifyObservers();
/*     */       }
/*     */     } 
/* 149 */     finish();
/*     */   }
/*     */   
/*     */   public void finish() {
/* 153 */     if (this.evaluator.holesInSubjectInDay) {
/* 154 */       EventMutator.group(this.best, this.evaluator, this.terms);
/*     */     }
/* 156 */     notifyObservers();
/*     */   }
/*     */   
/*     */   private void notifyObservers() {
/* 160 */     for (int i = 0; i < this.observers.size(); i++) {
/* 161 */       ((ClassObserver)this.observers.get(i)).valueChanged(this.best);
/*     */     }
/*     */   }
/*     */   
/*     */   private void evaluate(ScheduleSolution solution) {
/* 166 */     solution.affinity = this.evaluator.evaluate(solution.solution);
/*     */   }
/*     */   
/*     */   private void cloning() {
/* 170 */     Arrays.sort(this.population, Comparator.naturalOrder());
/* 171 */     int index = 0;
/* 172 */     for (int i = 1; i <= this.population.length; i++) {
/* 173 */       ScheduleSolution s = this.population[i - 1];
/* 174 */       int copies = (int)((this.paramBeta * this.paramN) / i + 0.5D);
/* 175 */       for (int j = 0; j < copies; j++) {
/* 176 */         ScheduleSolution c = new ScheduleSolution(Event.copyOf(s.solution), s.affinity);
/* 177 */         this.clonedPopulation[index] = c;
/* 178 */         this.clonedPopulationRanks[index] = i;
/* 179 */         index++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void parallelEvaluation(ScheduleSolution[] solutions) {
/* 185 */     int numberOfTracks = 8 * Runtime.getRuntime().availableProcessors();
/* 186 */     List<Future<Void>> results = new ArrayList<>();
/* 187 */     int numberPerTrack = solutions.length / numberOfTracks;
/*     */     
/* 189 */     for (int i = 0; i < numberOfTracks; i++) {
/* 190 */       int yMin = i * numberPerTrack;
/* 191 */       int yMax = (i + 1) * numberPerTrack - 1;
/* 192 */       if (i == numberOfTracks - 1) {
/* 193 */         yMax = solutions.length - 1;
/*     */       }
/* 195 */       EvaluationJob job = new EvaluationJob(yMin, yMax, solutions);
/* 196 */       results.add(this.pool.submit(job));
/*     */     } 
/* 198 */     for (Future<Void> futureResult : results) {
/*     */       try {
/* 200 */         futureResult.get();
/* 201 */       } catch (InterruptedException|java.util.concurrent.ExecutionException interruptedException) {}
/*     */     } 
/*     */   }
/*     */   
/*     */   private void parallelHyperMutation() {
/* 206 */     int numberOfTracks = 8 * Runtime.getRuntime().availableProcessors();
/* 207 */     List<Future<Void>> results = new ArrayList<>();
/* 208 */     int numberPerTrack = this.clonedPopulationSize / numberOfTracks;
/*     */     
/* 210 */     for (int i = 0; i < numberOfTracks; i++) {
/* 211 */       int yMin = (i == 0) ? 1 : (i * numberPerTrack);
/* 212 */       int yMax = (i + 1) * numberPerTrack - 1;
/* 213 */       if (i == numberOfTracks - 1) {
/* 214 */         yMax = this.clonedPopulationSize - 1;
/*     */       }
/* 216 */       HyperMutationJob job = new HyperMutationJob(yMin, yMax);
/* 217 */       results.add(this.pool.submit(job));
/*     */     } 
/*     */     
/* 220 */     for (Future<Void> futureResult : results) {
/*     */       try {
/* 222 */         futureResult.get();
/* 223 */       } catch (InterruptedException|java.util.concurrent.ExecutionException interruptedException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void select() {
/* 229 */     Arrays.sort(this.clonedPopulation, Comparator.naturalOrder());
/* 230 */     for (int i = 0; i < this.population.length; i++) {
/* 231 */       this.population[i] = new ScheduleSolution(Arrays.<Event[]>copyOf((this.clonedPopulation[i]).solution, (this.clonedPopulation[i]).solution.length), (this.clonedPopulation[i]).affinity);
/*     */     }
/*     */   }
/*     */   
/*     */   private void birthAndReplace() {
/* 236 */     int offset = this.population.length - this.paramD;
/* 237 */     for (int i = 0; i < this.paramD; i++) {
/* 238 */       this.population[offset + i] = randomGenerateSolution();
/*     */     }
/*     */   }
/*     */   
/*     */   public int getIteration() {
/* 243 */     return this.iteration;
/*     */   }
/*     */   
/*     */   public void setIteration(int iteration) {
/* 247 */     this.iteration = iteration;
/*     */   }
/*     */   
/*     */   public static FERDB getDatabase() {
/* 251 */     return database;
/*     */   }
/*     */   
/*     */   public int getParamN() {
/* 255 */     return this.paramN;
/*     */   }
/*     */   
/*     */   public int getParamD() {
/* 259 */     return this.paramD;
/*     */   }
/*     */   
/*     */   public int getParamBeta() {
/* 263 */     return this.paramBeta;
/*     */   }
/*     */   
/*     */   public int getClonedPopulationSize() {
/* 267 */     return this.clonedPopulationSize;
/*     */   }
/*     */   
/*     */   public int getPrintParameter() {
/* 271 */     return this.printParameter;
/*     */   }
/*     */   
/*     */   public int getMaxMutations() {
/* 275 */     return this.maxMutations;
/*     */   }
/*     */   
/*     */   public int getIterationLimit() {
/* 279 */     return this.iterationLimit;
/*     */   }
/*     */   
/*     */   public GroupClass[] getGroups() {
/* 283 */     return this.groups;
/*     */   }
/*     */   
/*     */   public List<Term> getTerms() {
/* 287 */     return this.terms;
/*     */   }
/*     */   
/*     */   public Room[] getRooms() {
/* 291 */     return this.rooms;
/*     */   }
/*     */   
/*     */   public EventEvaluator getEvaluator() {
/* 295 */     return this.evaluator;
/*     */   }
/*     */   
/*     */   public Random getRand() {
/* 299 */     return this.rand;
/*     */   }
/*     */   
/*     */   public ScheduleSolution getBest() {
/* 303 */     return this.best;
/*     */   }
/*     */   
/*     */   public ScheduleSolution[] getPopulation() {
/* 307 */     return this.population;
/*     */   }
/*     */   
/*     */   public ScheduleSolution[] getClonedPopulation() {
/* 311 */     return this.clonedPopulation;
/*     */   }
/*     */   
/*     */   public int[] getClonedPopulationRanks() {
/* 315 */     return this.clonedPopulationRanks;
/*     */   }
/*     */   
/*     */   public List<ClassObserver> getObservers() {
/* 319 */     return this.observers;
/*     */   }
/*     */   
/*     */   private class EvaluationJob implements Callable<Void> {
/*     */     int yMin;
/*     */     int yMax;
/*     */     ScheduleSolution[] solutions;
/*     */     
/*     */     public EvaluationJob(int yMin, int yMax, ScheduleSolution[] solutions) {
/* 328 */       this.yMin = yMin;
/* 329 */       this.yMax = yMax;
/* 330 */       this.solutions = solutions;
/*     */     }
/*     */ 
/*     */     
/*     */     public Void call() throws Exception {
/* 335 */       for (int index = this.yMin; index <= this.yMax; index++) {
/* 336 */         ClonAlg.this.evaluate(this.solutions[index]);
/*     */       }
/*     */       
/* 339 */       return null;
/*     */     }
/*     */   }
/*     */   
/*     */   private class HyperMutationJob implements Callable<Void> {
/*     */     int yMin;
/*     */     int yMax;
/*     */     
/*     */     public HyperMutationJob(int yMin, int yMax) {
/* 348 */       this.yMin = yMin;
/* 349 */       this.yMax = yMax;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public Void call() throws Exception {
/* 355 */       for (int index = this.yMin; index <= this.yMax; index++) {
/* 356 */         ScheduleSolution c = ClonAlg.this.clonedPopulation[index];
/*     */         
/* 358 */         double reciprocalAffinity = (c.affinity < 0.1D) ? 1.0D : (c.affinity * 10.0D);
/* 359 */         int mutations = (int)(11.0D - reciprocalAffinity) + ClonAlg.this.offsetMutations;
/* 360 */         if (mutations > ClonAlg.this.maxMutations) {
/* 361 */           mutations = ClonAlg.this.maxMutations;
/*     */         }
/*     */         
/* 364 */         for (int attempt = 0; attempt < mutations; attempt++) {
/* 365 */           EventMutator.mutate(c, ClonAlg.this.evaluator, ClonAlg.this.terms, c.affinity);
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 370 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/josipmrden/Documents/Josip Mrden_seminar/Student-Schedule-Optimizer-Clonalg/ClonAlgScheduler.jar!/hr/fer/java/zemris/seminar/clonalg/ClonAlg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */