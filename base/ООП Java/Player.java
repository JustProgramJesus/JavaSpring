import java.util.Random;

public class Player {
    private VARIANTS choice;
    private String name;

    // Конструктор для бота
    public Player() {
        this.name = "Bot";
        this.choice = getRandomVariant();
    }

    // Конструктор для игрока
    public Player(VARIANTS choice, String name) {
        this.choice = choice;
        this.name = name;
    }

    // Получаем случавйный вариант
    private VARIANTS getRandomVariant() {
        VARIANTS[] values = VARIANTS.values();
        Random random = new Random();
        return values[random.nextInt(values.length)];
    }

    // Здесь определяется победитель
    public String whoWins(Player p1, Player p2) {
        VARIANTS choice1 = p1.choice;
        VARIANTS choice2 = p2.choice;

        if (choice1 == choice2) {
            return "Ничья";
        }

        if ((choice1 == VARIANTS.ROCK && choice2 == VARIANTS.SCISSORS) ||
                (choice1 == VARIANTS.PAPER && choice2 == VARIANTS.ROCK) ||
                (choice1 == VARIANTS.SCISSORS && choice2 == VARIANTS.PAPER)) {
            return "Победил игрок с именем: " + p1.name;
        } else {
            return "Победил игрок с именем: " + p2.name;
        }
    }

    // Геттер для имени
    public String getName() {
        return name;
    }

    // Геттер для выбора
    public VARIANTS getChoice() {
        return choice;
    }
}
