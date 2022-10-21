// Я бы все же создал "мебель" как абстрактный с парочкой реализаций, но буду делать по заданию :)

public class Furniture {
    private FurnitureType _type;
    private String _material;
    private String _name;
    private float _squareArea;
    private int _price;
    public Furniture() {
        this._type = FurnitureType.UNSPECIFIED;
        this._material = "разные";
        this._name = "Мебель-сюрприз";
        this._price = 500;
        this._squareArea = 1;
    }

    /**
     *
     * @param type Тип мебели
     * @param material Материал мебели
     * @param name Название мебели
     * @param price Цена мебели
     * @param squareArea Площади мебели
     * @throws AssertionError при пустой строке для материала и названия, цены меньше 0 или площади меньше 0 или больше максимального значения типа Float
     */
    public Furniture(FurnitureType type, String material, String name, int price, float squareArea) throws AssertionError {
        // В конструктор должны быть переданы корректные значения
        assert (material.length() != 0 && name.length() != 0 && price > 0 && (squareArea > 0 || squareArea < Float.MAX_VALUE));
        this._type = type;
        this._material = material;
        this._name = name;
        this._price = price;
        this._squareArea = squareArea;
    }
    public FurnitureType getType() {
        return this._type;
    }
    public String getMaterial() {
        return this._material;
    }
    public String getName() {
        return this._name;
    }
    public float getSquareArea() {
        return this._squareArea;
    }
    public int getPrice() {
        return this._price;
    }
    /**
     * Устанавливает существующий тип для мебили. См {@link FurnitureType}
     * @param type новый тип
     */
    public void setType(FurnitureType type) {
        this._type = type;
    }
    /**
     * Устанавливает новый материал для мебили.
     * @param newMaterial новый материал, не может быть пустой строкой
     * @throws InvalidSelectionException при передачи пустой строки
     *
     */
    public void setMaterial(String newMaterial) throws InvalidSelectionException {
        if(newMaterial.length() == 0) {
            throw new InvalidSelectionException("Поле материал не может быть пустым");
        }
        this._material = newMaterial;
    }

    /**
     * Устанавливает новое имя для мебели
     * @param newName новое имя
     * @throws InvalidSelectionException при передачи пустой строки
     */
    public void setName(String newName) throws InvalidSelectionException {
        if(newName.length() == 0) {
            throw new InvalidSelectionException("Поле названия мебели не может быть пустым");
        }
        this._name = newName;
    }

    /**
     * Устанавливает площадь мебели
     * @param newSquareArea новая площадь
     * @throws InvalidSelectionException при новой площади меньше 0 или больше максимального значения типа Float
     */
    public void setSquareArea(float newSquareArea) throws InvalidSelectionException {
        if(newSquareArea <= 0 || newSquareArea > Float.MAX_VALUE) {
            throw new InvalidSelectionException("Площадь мебели не может быть меньше 0 и больше " + Float.MAX_VALUE);
        }
        this._squareArea = newSquareArea;
    }

    /**
     * Устанавливает цену мебели
     * @param newPrice новая цена
     * @throws InvalidSelectionException при новой цене меньше 0
     */
    public void setPrice(int newPrice) throws InvalidSelectionException {
        if(newPrice <= 0) {
            throw new InvalidSelectionException("Цена мебели не может быть меньше 0");
        }
        this._price = newPrice;
    }

    /**
     * Метод считает стоимость доставки мебели исходя из ее характеристик и предоставленных данных
     * @param distanceKm дистанция для доставки, в километрах
     * @param unloadRequired нужна ли разгрузка
     * @return Цена доставки
     */
    public float getDeliveryPrice(float distanceKm, boolean unloadRequired) {
        int baseDeliveryPrice = this._price / 50;
        float areaModifier = this._squareArea * 100;
        float distanceModifier = baseDeliveryPrice * distanceKm;
        float deliveryOnly = (float) baseDeliveryPrice + areaModifier + distanceModifier;
        return unloadRequired ? deliveryOnly + 500 : deliveryOnly;
    }
}
