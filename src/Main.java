import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/*
    Работу выполнил студент ЗКИ21-16Б
    Филин Дмитрий Алексеевич
 */

/**
 * Необходимо реализовать класс согласно варианту, в том числе придумать поля (атрибуты)
 * для вашего варианта: числовые (целое и вещественное, не менее двух) и текстовые (не менее двух).
 * Все поля класса должны быть закрытыми, необходимо реализовать методы доступа.
 * В классе должны быть реализованы конструктор по умолчанию и с параметрами.
 * Защита ввода есть и на клиенте (простая валидация)
 * Унифицированы методы получения данных от пользователя
 * Правильнее было бы написать сюда строителя
 */
public class Main {
    /**
     * Преобразует тип мебели в человеческое название
     * @return Наименование типа мебели
     */
    public static String humanizeFurnitureType(FurnitureType type) {
        switch(type) {
            case BED -> {
                return "Кровать";
            }
            case SOFA -> {
                return "Диван";
            }
            case CHAIR -> {
                return "Кресло";
            }
            case CUPBOARD -> {
                return "Шкаф";
            }
            default -> {
                return "Не установлен";
            }
        }
    }

    /**
     * Возвращает значение типа Int считывая ввод пользователя
     * @param scanner {@link Scanner}
     * @return Результат сканирования
     * @throws InvalidInputException возникает при неудаче в приведении типов
     */
    public static int getIntInput(Scanner scanner) throws InvalidInputException {
        String input = scanner.nextLine();
        int r;
        try {
            r = Integer.parseInt(input);
            return r;
        } catch (NumberFormatException e) {
            // Цепочка исключений
            throw new InvalidInputException("Требуется значение типа Int");
//            return 0;
        }
    }
    /**
     * Возвращает значение типа Float считывая ввод пользователя
     * @param scanner {@link Scanner}
     * @return Результат сканирования
     * @throws InvalidInputException возникает при неудаче в приведении типов
     */
    public static float getFloatInput(Scanner scanner) throws InvalidInputException {
        String input = scanner.nextLine();
        float r;
        try {
            r = Float.parseFloat(input);
            return r;
        } catch (NumberFormatException e) {
            // Цепочка исключений
            throw new InvalidInputException("Требуется значение типа Float");
//            return 0;
        }
    }

    /**
     * Запрашивает от пользователя тип мебели
     * @return Тип мебели
     * @throws InvalidSelectionException недопустимый пункт меню
     */
    public static FurnitureType receiveNewFurnitureType() throws InvalidSelectionException {
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Выберите тип мебели:");
            int counter = 1;
            FurnitureType[] furnitureValues = FurnitureType.values();
            for (FurnitureType t : furnitureValues) {
                System.out.println(counter + ": " + humanizeFurnitureType(t));
                counter++;
            }
            try {
                int choice = getIntInput(input);
                if(choice < 1 || choice > furnitureValues.length) {
                    System.out.println("Недопустимое значение. Попробуйте еще раз.");
                    continue;
                }
                return furnitureValues[choice-1];
            } catch (InvalidInputException e) {
                // Цепочка исключений
                throw new InvalidSelectionException(e.getMessage() + " (поле: тип мебели)");
            }
        }
    }

    /**
     * Получает материал мебели с ввода пользователя
     * @return Строка материала мебели
     */
    public static String receiveNewFurnitureMaterial() {
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Введите материал мебели:");
            String newMaterial = input.nextLine();
            if(newMaterial.length() <= 1) {
                System.out.println("Недопустимое значение");
                continue;
            }
            return newMaterial;
        }
    }
    /**
     * Получает наименование мебели с ввода пользователя
     * @return Строка наименования мебели
     */
    public static String receiveNewFurnitureName() {
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Введите наименование мебели:");
            String newName = input.nextLine();
            if(newName.length() <= 1) {
                System.out.println("Недопустимое значение");
                continue;
            }
            return newName;
        }
    }

    /**
     * Получает новую площадь мебели с ввода пользователя
     * @return Площадь мебели
     * @throws InvalidSelectionException При вводе недопустимого значения
     */
    public static float receiveNewFurnitureSquareArea() throws InvalidSelectionException {
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Введите площадь мебели (используйте точку для нецелых значений):");
            try {
                float sqArea = getFloatInput(input);
                if (sqArea <= 0 || sqArea > Float.MAX_VALUE) {
                    System.out.println("Недопустимое значение. Попробуйте еще раз.");
                    continue;
                }
                return sqArea;
            } catch (InvalidInputException e) {
                // Цепочка исключений
                throw new InvalidSelectionException(e.getMessage() + " (поле: площадь мебели)");
            }
        }
    }

    /**
     * Получает новую цену мебели с ввода пользователя
     * @return Цена мебели
     * @throws InvalidSelectionException При вводе недопустимого значения
     */
    public static int receiveNewFurniturePrice() throws InvalidSelectionException {
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("Введите цену мебели (целое число):");
            try {
                int newPrice = getIntInput(input);
                if(newPrice <= 0) {
                    System.out.println("Недопустимое значение. Попробуйте еще раз.");
                    continue;
                }
                return newPrice;
            } catch (InvalidInputException e) {
                // Цепочка исключений
                throw new InvalidSelectionException(e.getMessage() + " (поле: цена мебели)");
            }

        }
    }

    /**
     * Создает новый экземпляр мебели
     * @return мебель
     * @throws InvalidSelectionException При вводе неверного значения в любом из селекторов. При любой ошибке экземпляр не будет создан
     */
    public static Furniture createNewFurniture() throws InvalidSelectionException {
        // Передача исключения наверх
        return new Furniture(receiveNewFurnitureType(), receiveNewFurnitureMaterial(), receiveNewFurnitureName(),
                receiveNewFurniturePrice(), receiveNewFurnitureSquareArea());
    }

    /**
     * Выводит на экран всю информацию о мебели
     * @param arr массив с мебелью
     */
    public static void listAllFurniture(ArrayList<Furniture> arr) {
        for (int i = 0; i < arr.size(); i++) {
            System.out.println(i+1 + ": " + arr.get(i).getName());
            System.out.println("Тип: " + humanizeFurnitureType(arr.get(i).getType()));
            System.out.println("Материал: " + arr.get(i).getMaterial());
            System.out.println("Площадь: " + arr.get(i).getSquareArea() + " м2");
            System.out.println("Цена: " + arr.get(i).getPrice() + " руб.");
            System.out.println("Цена доставки за километр: " + arr.get(i).getDeliveryPrice(1, false) + " руб.");
        }
    }

    /**
     * Сортирует мебель по выбранному полю
     * @param arr массив с мебелью
     * @throws InvalidInputException недопустимый пункт меню
     */
    public static void sortFurniture(ArrayList<Furniture> arr) throws InvalidInputException {
        // Передача исключения наверх
        Scanner input = new Scanner(System.in);
        int field;
        do {
            System.out.println("Введите номер поля, по которому нужно отсортировать мебель:");
            System.out.println("1. Название");
            System.out.println("2. Материал");
            System.out.println("3. Тип");
            System.out.println("4. Площадь");
            System.out.println("5. Цена");
            field = getIntInput(input);
        } while (field < 1 || field > 5);
        switch (field) {
            case 1 -> arr.sort(Comparator.comparing(Furniture::getName));
            case 2 -> arr.sort(Comparator.comparing(Furniture::getMaterial));
            case 3 -> arr.sort(Comparator.comparing(Furniture::getType));
            case 4 -> arr.sort(Comparator.comparing(Furniture::getSquareArea));
            case 5 -> arr.sort(Comparator.comparing(Furniture::getPrice));
        }
    }

    /**
     * Изменяет выбранное поле в выбранной мебели
     * @param arr массив с мебелью
     * @throws InvalidSelectionException При установке недопустимого значения
     * @throws InvalidInputException При вводе недопустимого пункта меню
     */
    public static void editFurniture(ArrayList<Furniture> arr) throws InvalidSelectionException, InvalidInputException {
        // Передача исключения наверх
        int index;
        Scanner input = new Scanner(System.in);
        do {
            listAllFurniture(arr);
            System.out.println("Введите номер мебели, который желаете отредактировать:");
            index = getIntInput(input);
        } while (index < 0 || index >= arr.size());
        int field;
        do {
            System.out.println("Введите номер поля, которое желаете редактировать:");
            System.out.println("1. Название");
            System.out.println("2. Материал");
            System.out.println("3. Тип");
            System.out.println("4. Площадь");
            System.out.println("5. Цена");
            field = getIntInput(input);
        } while (field < 1 || field > 5);
        switch (field) {
            case 1 -> arr.get(index-1).setName(receiveNewFurnitureName());
            case 2 -> arr.get(index-1).setMaterial(receiveNewFurnitureMaterial());
            case 3 -> arr.get(index-1).setType(receiveNewFurnitureType());
            case 4 -> arr.get(index-1).setSquareArea(receiveNewFurnitureSquareArea());
            case 5 -> arr.get(index-1).setPrice(receiveNewFurniturePrice());
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Furniture> furniture = new ArrayList<>();
        System.out.println("Управление мебелью");
        while(true) {
            System.out.println("Выберите пункт меню:");
            System.out.println("1. Добавить мебель сюрприз.");
            System.out.println("2. Добавить мебель по параметрам.");
            System.out.println("3. Редактировать мебель.");
            System.out.println("4. Посмотреть всю мебель (плюс цену доставки).");
            System.out.println("5. Отсортировать мебель.");
            System.out.println("0. Выйти");
            String choice = input.nextLine();
            int selectedMenuItem;
            try {
                selectedMenuItem = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели некорректный пункт меню!");
                continue;
            }
            switch (selectedMenuItem) {
                case 0:
                    return;
                case 1:
                    furniture.add(new Furniture());
                    System.out.println("Мебель-сюрприз добавлена!");
                    break;
                case 2:
                    try {
                        furniture.add(createNewFurniture());
                    } catch (InvalidSelectionException e) {
                        // Простой перехват исключения
                        System.out.println(e.getMessage());
                        break;
                    }
                    System.out.println("Мебель добавлена!");
                    break;
                case 3:
                    try {
                        editFurniture(furniture);
                    } catch (InvalidSelectionException | InvalidInputException ignored) {}
                    // Подавление исключения
                    break;
                case 4:
                    listAllFurniture(furniture);
                    break;
                case 5:
                    try {
                        sortFurniture(furniture);
                    } catch (InvalidInputException e) {
                        // Простой перехват исключения
                        System.out.println("Недопустимый пункт меню");
                    }
                    break;
                default:
                    System.out.println("Вы ввели некорректный пункт меню!");
                    break;
            }
        }
    }
}