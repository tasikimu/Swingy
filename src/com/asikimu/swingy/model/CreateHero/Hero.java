package com.asikimu.swingy.CreateHero;

public class Hero {
  @NotNull
        private String newHero;
        @NotNull
        private HeroStats stats = new HeroStats();
        @NotNull
        private Artifacts artifact;

        public Hero(){

        }
    
        protected Hero (String newHero, HeroStats stats, Artifacts artifact){
            this.newHero = newHero;
            this.stats = stats;
            this.artifact = artifact;
        }

        public HeroStats getHeroStats() {
            return stats;
        }

        public Artifacts getArtifact(){
            return artifact;
        }

        public String getnewHero(){
            return this.newHero;
        }

        public void setArtifact(Artifacts artifact){
            this.artifact = artifact;
        }
}
