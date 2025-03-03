package scrapping.scrap;

public class jobDetails {
    public String experienceLevel;
    public String function;
    public String activity;
    public String niveauEtude;

    public jobDetails(String experienceLevel, String function, String activity, String niveauEtude) {
        this.experienceLevel = experienceLevel;
        this.function = function;
        this.activity = activity;
        this.niveauEtude = niveauEtude;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public String getFunction() {
        return function;
    }

    public String getActivity() {
        return activity;
    }

    public String getNiveauEtude() {
        return niveauEtude;
    }

    @Override
    public String toString() {
        return "Experience: " + experienceLevel +
                ", Function: " + function +
                ", Activity: " + activity +
                ", Niveau d'Étude: " + niveauEtude;
    }
}
