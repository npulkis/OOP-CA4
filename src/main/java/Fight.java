import DTOs.Fighter;

public class Fight {
    private Fighter TitleHolder;
    private Fighter Opponent;
    private Arena arena;
    private String Date;

    Fight(Fighter titleHolder, Fighter opponent, Arena arena, String date){
        this.TitleHolder = titleHolder;
        this.Opponent = opponent;
        this.arena = arena;
        this.Date = date;
    }
}
