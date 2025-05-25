public class NPC {
    private String name;
    private String dialogue;
    private boolean defeated;

    public NPC(String name, String dialogue) {
        this.name = name;
        this.dialogue = dialogue;
        this.defeated = false;
    }

    public String getName() {
        return name;
    }

    public String getDialogue() {
        return dialogue;
    }

    public boolean isDefeated() {
        return defeated;
    }

    public void defeat() {
        this.defeated = true;
    }
}
