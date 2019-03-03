import java.util.*;

public class Main {

    public static void main(String[] args) {

        List<Employee> employees = data();

        Map<Integer, List<Employee>> byManagerId = new HashMap<>();

        for (Employee e : employees) {
            List<Employee> data = byManagerId.get(e.getManagerId());

            if (data == null) {
                data = new ArrayList<>();
            }

            data.add(e);

            byManagerId.put(e.getManagerId(), data);
        }

        Employee director = findDirector(employees);

        printEmployee(director, byManagerId);

        System.out.println();

        Employee employeeForSearch = findEmployeeByName("peter evans", employees);

        printSalarys(byManagerId, employeeForSearch);

        employeeForSearch = findEmployeeByName("peter evans",employees);

        System.out.println();

        printRatioForEmployee(byManagerId,employeeForSearch);

    }

    private static void printRatioForEmployee(Map<Integer, List<Employee>> byManagerId, Employee employee) {

        List<Employee> employees = byManagerId.get(employee.getId());

        if(employees == null){
            return;
        }

        double totalRatio = 0;
        double averageDepartmentRatio = 0;

        for (Employee e : employees) {
            totalRatio += e.getRatio();
            averageDepartmentRatio = totalRatio / employees.size();
        }

        System.out.printf("Ratio for %s department is %.2f %n", employee.getName(), averageDepartmentRatio );

        for(Employee em : employees) {
            printRatioForEmployee(byManagerId,em);
        }

    }

    private static Employee findEmployeeByName(String name, List<Employee> employees) {
        for (Employee e : employees) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        throw new RuntimeException("No employee with given name");
    }

    private static void printSalarys(Map<Integer, List<Employee>> byManagerId, Employee e) {

        List<Employee> employees = byManagerId.get(e.getId());

        if (employees == null) {
            return;
        }
        int total = 0;

        for (Employee employee : employees) {
            total += employee.getSalary();
        }

        System.out.printf("Total money of %s employees is %d %n", e.getName(), total + e.getSalary());

        for(Employee em : employees) {
            printSalarys(byManagerId,em);
        }
    }

    private static void printEmployee(Employee e, Map<Integer, List<Employee>> byManagerId) {

        System.out.printf("%d %s %s %n", e.getId(), e.getName(), e.getDepartment());

        List<Employee> employees = byManagerId.get(e.getId());

        if (employees == null) {
            return;
        }

        System.out.println("employees");
        for (Employee emp : employees) {
            System.out.printf("\t %s %s %n", emp.getName(), emp.getDepartment());
        }

        for (Employee emp : employees) {

            if (byManagerId.containsKey(emp.getId())) {
                printEmployee(emp, byManagerId);
            }
        }
    }

    private static Employee findDirector(List<Employee> data) {
        for (Employee e : data) {
            if (e.getManagerId() == 0) {
                return e;
            }
        }

        throw new RuntimeException("No director found");
    }

    private static List<Employee> data() {
        return Arrays.asList(
                new Employee(1, "john smith", "it", 1000, 2, 5),
                new Employee(2, "neil adams", "it", 3000, 4, 2),
                new Employee(3, "maria christina", "stats", 3000, 4, 3),
                new Employee(4, "peter evans", "management", 5000, 0, 6)
        );
    }
}