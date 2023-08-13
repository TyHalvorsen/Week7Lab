
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>manage user data</title>
    </head>
    <body>
        <h1>Manage User Data</h1>
        <form method="post" action="users">
            
            <input class="input" type="email" name="email" placeholder="Email"><br>
           
            <input class="input" type="text" name="fName" placeholder="First Name"><br>
           
            <input class="input" type="text" name="lName" placeholder="Last Name"><br>
           
            <input  class="input"type="password" name="password" placeholder="Password"><br>
           
            <select  class="dropdown" name="roles" id="roles">
                <option value="System Admin">System Admin</option>
                <option value="Regular User">Regular User</option>
                <option value="Company Admin">Company Admin</option>
            </select><br>
            <a class="checkbox" style="">Active<input style="" class="checkbox" type="checkbox" name="active" placeholder="" value=""></a> <br>
            <input type="submit" name="Add" value="Add" id="saveBtn" style=""><br>
        </form>
        <br>
        <h1 id="error">${errorMessage}</h1>
        <br>
        <div class="column">
            <table class="manage">
                <tr style="">
                    <td style="">Role</th>
                    <td style="">Email</th>
                    <td style="">First</th>
                    <td style="">Last</th>
                    <td style="">Action</th> 
                </tr>

            <c:forEach items="${users}" var="user">
                <c:if test="${user.getActive()}">
                        <tr style="color: white" >
                            <td class="manage" style="width: 20%;">
                                ${user.getRole()}
                            </td>
                            <td class="manage" style="width: 20%;">
                                ${user.getEmail()}
                            </td>
                            <td class="manage" style="width: 20%;">
                                ${user.getFirstname()}
                            </td>
                            <td class="manage" style="width: 20%;">
                                ${user.getLastname()}
                            </td>
                            <td class="manage" style="width: 20%;">
                                <a href="edit?action=edit&email=${user.getEmail()}" style="" id="edit"></a> 
                                <a href="delete?action=delete&email=${user.getEmail()}"></a> 
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </div>
        <div class="column">
            <div class="card">
                <form method="post" action="users">
                    <h1 class="headers" style="" >Edit Users</h1>
                    <input class="input"type="email" placeholder="Email" name="emailEdit" value="${emailEdit}" readonly=""><br>
                    <input class="input"type="text" placeholder="First Name" name="firstnameEdit" value="${firstnameEdit}" > <br>
                    <input class="input"type="text"placeholder="Last Name" name="lastnameEdit" value="${lastnameEdit}"><br>
                    <input class="input"type="password"placeholder="Password" name="passwordEdit" value="${passwordEdit}"><br>
                    <select class='dropdown' name="roleEdit" id="roles" value="${dropdownEdit}">
                        <option value="System Admin">System Admin</option>
                        <option value="Regular User">Regular User</option>
                        <option value="Company Admin">Company Admin</option>
                    </select>
                        <br>
                    <a class="checkbox" name="activeEdit" style="">Active<input style="" class="checkbox" type="checkbox" name="activeEdit" placeholder="" value=""></a> 
                    <br>
                    <input type="submit"name="save" value="Save" id="saveBtn">
                    <br>
                    <input type="submit"name="cancel" value="Cancel" id="cancelBtn">
                    <br>
                </form>
                <h1>
                    ${errorMessage}
                </h1>

            </div>
        </div>
        </div>
    </body>
</html>
