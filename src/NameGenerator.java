import java.util.ArrayList;

/**
 * Класс генерирует случайную связку Имя+Фамилия на русском языке для использования в учебных программах
 * Для использования необходимо создать экземпляр класса (в момент создания будет выполнено заполнения
 * массивов возможных имен и фамилий). Для получения случайного имени+фамилии у созданного объекта необходимо вызвать
 * метод {@link #getName}.
 */
public class NameGenerator {

    private final ArrayList<FirstName> firstName = new ArrayList<>(200);
    private final ArrayList<String> lastName = new ArrayList<>(100);

    public NameGenerator()  {

        setNameLists();
    }
    private void setNameLists() {
        for (FemaleNames name: FemaleNames.values()) {
            this.firstName.add(new FirstName(name.toString(),'f'));
        }
        for (MaleNames name: MaleNames.values()) {
            this.firstName.add(new FirstName(name.toString(),'m'));
        }
        for (LastNames surname: LastNames.values()) {
            this.lastName.add(surname.toString());
        }
    }

    /**
     * Возвращает строку Имя+Фамилия.
     * @param nameAtFirstPlace  если {@code true}, то будет возвращена строка Имя+Фамилия, иначе Фамилия+Имя
     * @return строку Имя+Фамилия
     */
    public String getName(boolean nameAtFirstPlace){
        StringBuilder result = new StringBuilder();
        FirstName fn = firstName.get((int) (Math.random()*firstName.size()));
        String resultLastName = lastName.get((int) (Math.random()*lastName.size()));
        if (fn.gender =='f') {
            if(
                    resultLastName.endsWith("ий") ||
                    resultLastName.endsWith("ой") ||
                    resultLastName.endsWith("ый")
            ) {
               resultLastName = resultLastName.substring(0,resultLastName.length()-2) + "ая";
            } else if (
                    resultLastName.endsWith("ов") ||
                    resultLastName.endsWith("ёв") ||
                    resultLastName.endsWith("ей") ||
                    resultLastName.endsWith("ин") ||
                    resultLastName.endsWith("ын")
            ) {
                resultLastName += "a";
            }
        }
        if(!nameAtFirstPlace){
            result.append(resultLastName).append(" ").append(fn.firstName);
        } else {
            result.append(fn.firstName).append(" ").append(resultLastName);
        }
        return result.toString();
    }

    static class FirstName {
        public FirstName(String firstName, char gender) {
            this.firstName = firstName;
            this.gender = gender;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            FirstName firstName1 = (FirstName) o;

            return firstName.equals(firstName1.firstName);
        }

        @Override
        public int hashCode() {
            return firstName.hashCode();
        }

        @Override
        public String toString() {
            return firstName;
        }

        private final String firstName;
        private final char gender;
    }
}
