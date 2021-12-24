import java.io.*;

public class FileHelper {

    String data;
    String filename;
    String filePath;
    String choice;
    String choice2;
    RandomAccessFile file;
    BufferedReader in;

    public FileHelper(String filePath) throws UnsupportedEncodingException {
        this.in = new BufferedReader(new InputStreamReader(System.in, "Cp1251"));
        this.filePath = filePath;
    }

    public void runApp() throws IOException {
        while (true) {
            printMenu();
            choice = in.readLine();
            if (choice.compareTo("1") == 0) {
                printTextFromFile();
            } else if (choice.compareTo("2") == 0) {
                printRedactMenu();
                choice2 = in.readLine();
                if (choice2.compareTo("1") == 0) {
                    addStart();
                } else if (choice2.compareTo("2") == 0) {
                    addEnd();
                } else if (choice2.compareTo("3") == 0) {
                    addRandom();
                }
            } else if (choice.compareTo("3") == 0) {
                printTextBetweenBrackets();
            } else if (choice.compareTo("4") == 0) {
                return;
            }
        }
    }

    public void printMenu() {//метод вывода меню на экран
        System.out.println("Введите ваш выбор:");
        System.out.println("1.Прочитать текст из файла");
        System.out.println("2.Редактировать текст в файле");
        System.out.println("3.Выполнить действие над текстом из файла");
        System.out.println("4.Выход");
    }

    public void textIntoFile() throws IOException {//метод ввода текста и записи его в файл
        System.out.println("Введите текст:");
        data = in.readLine();//ввод текста
        System.out.println("Введите имя файла:");
        filename = in.readLine();
        file = new RandomAccessFile(new File(filename), "rw");
        file.writeBytes(data);
        file.close();
        System.out.println("Выш текст сохранен.");
    }

    public void printRedactMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1 - добавление текста в начало файла");
        System.out.println("2 - добавление текста в конец файла");
        System.out.println("3 - добавление текста в произвольную позицию в файле");
    }

    public void addStart() throws IOException {
        file = new RandomAccessFile(new File(filePath), "rw");
        data = file.readLine();
        System.out.println("Введите строку для добавления в начало:");
        String s;
        s = in.readLine();
        file.seek(0);
        file.writeBytes(s);
        file.seek(s.length());
        file.writeBytes(data);
        file.close();
        System.out.println("Строка записана в начало файла.");
    }

    public void addEnd() throws IOException {
        file = new RandomAccessFile(new File(filePath), "rw");
        data = file.readLine();
        System.out.println("Введите строку для добавления в конец:");
        String s;
        s = in.readLine();
        file.seek(file.length());
        file.writeBytes(s);
        file.close();
        System.out.println("Строка записана в конец файла.");
    }

    public void addRandom() throws IOException {
        file = new RandomAccessFile(new File(filePath), "rw");
        System.out.println("Введите строку для добавления в указанную позицию в файле:");
        String s;
        s = in.readLine();
        System.out.println("Введите необходимую позицию в файле:");
        int n;
        n = Integer.parseInt(in.readLine());
        file.seek(n);
        data = file.readLine();
        file.seek(n);
        file.writeBytes(s);
        file.writeBytes(data);
        file.close();
        System.out.println("Строка записана в файл.");
    }

    public void printTextBetweenBrackets() throws IOException {
        String text = getTextFromFile();
        StringBuilder sb = new StringBuilder();
        char ch;
        boolean isInBracket = false;

        for (int i = 0; i < text.length(); i++) {
            ch = Character.toLowerCase(data.charAt(i));
            if (ch == '«')
                isInBracket = true;
            if (ch == '»')
                isInBracket = false;
            if (isInBracket)
                sb.append(Character.toLowerCase(data.charAt(i + 1)));
        }

        System.out.println("Слова помещенные между символами « »: " + sb);
    }

    public void printTextFromFile() throws IOException {
        System.out.println("Информация из файла: " + getTextFromFile());
    }

    private String getTextFromFile() throws IOException {
        file = new RandomAccessFile(new File(filePath), "r");
        data = file.readLine();
        file.close();

        return data;
    }
}

