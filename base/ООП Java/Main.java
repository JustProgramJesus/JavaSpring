enum VARIANTS {
    ROCK, PAPER, SCISSORS
}

public class Main {
    public static void main(String[] args) {
        // Создем объекты
        Player bot = new Player();
        Player alex = new Player(VARIANTS.SCISSORS, "Alex");

        // Получаем результат
        System.out.println(bot.whoWins(bot, alex));
    }
}
