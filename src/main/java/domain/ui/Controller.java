package domain.ui;

import domain.db.DbException;
import domain.db.PersonService;
import domain.model.DomainException;
import domain.model.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private PersonService db = new PersonService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request,response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        String destination;
        if(command == null) command = "";

        switch (command) {
            case "Overview":
                destination = overzicht(request,response);
                break;
            case "Register":
                destination = register(request,response);
                break;
            case "addPerson":
                destination = addPerson(request,response);
                break;
            case "login":
                destination = login(request,response);
                break;
            case "logout":
                destination = logout(request,response);
                break;
            case "searchForm":
                destination = searchForm(request,response);
                break;
            case "search":
                destination = search(request,response);
                break;

            default:
                destination = home(request,response);
        }
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request,response);
    }



    private String home(HttpServletRequest request, HttpServletResponse response) {
        return "index.jsp";
    }


    private String overzicht(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("persons", db.getAll());
        return "personoverview.jsp";
    }

    private String register(HttpServletRequest request, HttpServletResponse response) {
        return "register.jsp";
    }

    private String login(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("userid").trim().toLowerCase();
        String password = request.getParameter("password").trim();
        if(db.correctLogin(userId, password)) {
            HttpSession session = request.getSession();
            Person person = db.get(userId);
            session.setAttribute("person", person);
            session.setAttribute("loggedIn", true);
            return home(request,response);
        }
        else{
            request.setAttribute("error", "“No valid userid/password”");
            return home(request,response);
        }
    }

    private String logout(HttpServletRequest request, HttpServletResponse response) {
        //String userId = request.getParameter("userId");
        HttpSession session = request.getSession();
        session.removeAttribute("person");
        session.setAttribute("loggedIn", false);
        return home(request,response);
    }

    private String searchForm(HttpServletRequest request, HttpServletResponse response) {
        return "searchform.jsp";
    }

    private String search(HttpServletRequest request, HttpServletResponse response) {
        String userid = request.getParameter("userid").trim().toLowerCase();
        if(userid.isEmpty()){
            request.setAttribute("error","Gelieve een id in te vullen.");
            return "searchform.jsp";
        }
        Person person = db.get(userid);
        if(person == null){
            request.setAttribute("error","De student zit niet in onze database");
            return "searchform.jsp";
        } else{
            request.setAttribute("person", person);
            return "found.jsp";
        }

    }

    private String addPerson(HttpServletRequest request, HttpServletResponse response) {
// lijst klaarzetten om foutboodschappen op te slaan
        ArrayList<String> errors = new ArrayList<String>();

        Person person = new Person(); //lege persoon aanmaken, daarna één voor één de var aan toevoegen met Set.

        setUserId(person, request, errors); //method aanmaken binnen de servlet om na te kijken of de waarde geldig is en deze dan op te slaan.
        setFirstName(person,request,errors);
        setLastName(person,request,errors);
        setEmail(person,request,errors);
        setPassword(person,request,errors);

        if(errors.size()==0){
            try{
                db.add(person); //geen errors ? dan voeg nieuwe fiets toe
                return home(request,response);
            } catch(DbException e){ //als duplicaat, geef error terug
                errors.add(e.getMessage());
                request.setAttribute("errors", errors);
                return register(request,response);
            }
        } else{ //als er errors zijn geef deze terug
            request.setAttribute("errors", errors);
            return register(request,response);
        }
    }

    private void setUserId(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String userId = request.getParameter("userid").toLowerCase(); // to lower case to make matchin case insensitive
        try{
            person.setUserid(userId); //setItem al op voorhand gegeven
            request.setAttribute("vorigeUserId", userId); //bewaar de correcte itemId
        } catch(DomainException e){
            errors.add(e.getMessage()); //als een foute is dan wordt de exception toegevoegd aan de list van errors
        }
    }
    private void setFirstName(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String firstName = request.getParameter("firstName");
        try{
            person.setFirstName(firstName);
            request.setAttribute("vorigeFirstName", firstName);
        } catch(DomainException e){
            errors.add(e.getMessage());
        }
    }

    private void setLastName(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String lastName = request.getParameter("lastName");
        try{
            person.setLastName(lastName);
            request.setAttribute("vorigeLastName", lastName);
        } catch(DomainException e){
            errors.add(e.getMessage());
        }
    }

    private void setEmail(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String email = request.getParameter("email");
        try{
            person.setEmail(email);
            request.setAttribute("vorigeEmail", email);
        } catch(DomainException e){
            errors.add(e.getMessage());
        }
    }

    private void setPassword(Person person, HttpServletRequest request, ArrayList<String> errors) {
        String password = request.getParameter("password");
        try{
            person.setPassword(password);
            request.setAttribute("vorigePassword", password);
        } catch(DomainException e){
            errors.add(e.getMessage());
        }
    }
}
