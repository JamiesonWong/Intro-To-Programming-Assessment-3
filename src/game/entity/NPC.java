package game.entity;

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

    public void talk() {
        System.out.println(name + ": " + dialogue);
    }

    public boolean isDefeated() {
        return defeated;
    }

    public void defeat() {
        this.defeated = true;
    }
} 