package OOPHW1;
public class Products {
        protected String name;
        protected int price;
        protected int count;
        protected String unitOfMeasurement;
        /**
         * Конструктор Продуктов    
         * @param name Наименование продукта
         * @param price Цена продукта
         * @param count Количество продукта
         * @param unitOfMeasurement Единица измерения
         */
        public Products (String name, int price, int count, String unitOfMeasurement){
            this.name = name;
            this.price = price;
            this.count = count;
            this.unitOfMeasurement = unitOfMeasurement;
        }
        public String getInfo(){
            return String.format("Наименование: %s;\nЦена: %d;\nКоличество: %d;\nЕдиница измерения: %s;", name, price, count, unitOfMeasurement);
        }
}
