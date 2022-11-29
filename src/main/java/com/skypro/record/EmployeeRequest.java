package com.skypro.record;

import com.skypro.exception.EmployeeException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private int department;
    private int salary;

//    Character[] chars = {'A','B','S','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y',
//            'Z','a','b', 'c','d','e','f','g','h','i','g','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
//    private static final String VALID_CHARACTERS="ABSDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz";
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws EmployeeException {
        if (!StringUtils.isAlpha(firstName)) {
            //throw new EmployeeException("The name must contain only letters");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        this.firstName = StringUtils.capitalize(firstName.toLowerCase());
    }
//    public void setFirstName(String firstName) {
//        if (!StringUtils.containsOnly(firstName, 'A','B','S','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y',
//                'Z','a','b', 'c','d','e','f','g','h','i','g','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z')) {
//            throw new IllegalArgumentException("The name must contain only letters");
//        }
//        StringBuilder sbFirstName = new StringBuilder(firstName.toLowerCase());
//        sbFirstName.setCharAt(0, Character.toUpperCase(sbFirstName.charAt(0)));
//        String firstNameUp = sbFirstName.toString();
//        this.firstName = firstNameUp;
//    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws EmployeeException {
        if (!StringUtils.isAlpha(lastName)) {
            //throw new IllegalArgumentException("The name must contain only letters");
            throw new EmployeeException("The name must contain only letters");
        }
        this.lastName = StringUtils.capitalize(lastName.toLowerCase());
    }
//    public void setLastName(String lastName) {
//        for (int i = 0; i < lastName.length(); i++) {
//            if (!VALID_CHARACTERS.contains(String.valueOf(lastName.charAt(i)))) {
//                throw new IllegalArgumentException("The name must contain only letters");
//            }
//        }
//        StringBuilder sbLastName = new StringBuilder(lastName.toLowerCase());
//        sbLastName.setCharAt(0, Character.toUpperCase(sbLastName.charAt(0)));
//        String lastNameUp = sbLastName.toString();
//        this.lastName = lastNameUp;
//    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
