package emp.portal;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Service
@Data
public class PermissionModel  {

    public Map<String, Map<String, String>> compareNLConnectPermission(List<String> role)
    {
        Map<String, String> hrPrivileges= new HashMap<>();
        hrPrivileges.put("Read","Read");
        hrPrivileges.put("Add","Add");
        hrPrivileges.put("Delete","Delete");

        Map<String,String> adminPermission= new HashMap<>();
        adminPermission.put("Read","Read");
        adminPermission.put("Add","Add");
        adminPermission.put("Delete","Delete");

        Map<String,String> managerPermission= new HashMap<>();
        managerPermission.put("Read","Read");
        managerPermission.put("Add","Add");

        Map<String,String> emppermission= new HashMap<>();
        emppermission.put("Read","Read");
        emppermission.put("Add","Add");

        Map<String,String> AboutMe= new HashMap<>();
        AboutMe.put("Read","Read");
        AboutMe.put("Add","Add");
        Map<String,String> ManagerUi = new HashMap<>();
        ManagerUi.put("Read","Read");
        ManagerUi.put("Add","Add");
        Map<String,Map<String,String>> NL_ConnectPermission=new HashMap<>();
        NL_ConnectPermission.put("AboutMe",AboutMe);
        NL_ConnectPermission.put("NLSearch",ManagerUi);

        if (role.toString().equals("[ROLE_HR]") || role.toString().equals("[ROLE_Emp, ROLE_HR]") || role.toString().equals("[ROLE_Emp, ROLE_HR, ROLE_Manager]"))
        {
            Map<String, String> map1 = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            Map<String,String> map3= new HashMap<>();
            map1.putAll(hrPrivileges);
            map2.putAll(AboutMe);
            map3.putAll(ManagerUi);

            Map<String, String> commonMap = map2.entrySet().stream()
                    .filter(x -> map1.containsKey(x.getKey()))
                    .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
            Map<String, String> commonMap2 = map3.entrySet().stream()
                    .filter(x -> map1.containsKey(x.getKey()))
                    .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
            Map<String,Map<String,String>> combine= new HashMap<>();
            combine.put("AboutMe",commonMap);
            combine.put("NLSearch",commonMap2);
            return combine;

        }
         else if (role.toString().equals("[ROLE_ADMIN]"))
        {
            Map<String, String> map1 = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            map1.putAll(adminPermission);
            Map<String,String> map3= new HashMap<>();
            map2.putAll(AboutMe);
            map3.putAll(ManagerUi);

            Map<String, String> commonMap = map2.entrySet().stream()
                    .filter(x -> map1.containsKey(x.getKey()))
                    .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
            Map<String, String> commonMap2 = map3.entrySet().stream()
                    .filter(x -> map1.containsKey(x.getKey()))
                    .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
            Map<String,Map<String,String>> combine= new HashMap<>();
            combine.put("AboutMe",commonMap);
            combine.put("NLSearch",commonMap2);
            return combine;

        }
         else if (role.toString().equals("[ROLE_Manager]") || role.toString().equals("[ROLE_Emp, ROLE_Manager]") || role.toString().equals("[ROLE_Emp, ROLE_HR, ROLE_Manager]"))
        {
            Map<String, String> map1 = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            map1.putAll(managerPermission);
            Map<String,String> map3= new HashMap<>();
            map2.putAll(AboutMe);
            map3.putAll(ManagerUi);

            Map<String, String> commonMap = map2.entrySet().stream()
                    .filter(x -> map1.containsKey(x.getKey()))
                    .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
            Map<String, String> commonMap2 = map3.entrySet().stream()
                    .filter(x -> map1.containsKey(x.getKey()))
                    .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
            Map<String,Map<String,String>> combine= new HashMap<>();
            combine.put("AboutMe",commonMap);
            combine.put("NLSearch",commonMap2);
            return combine;
        }
        else if (role.toString().equals("[ROLE_Emp]"))
        {
            Map<String, String> map1 = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            map1.putAll(emppermission);
            Map<String,String> map3= new HashMap<>();
            map2.putAll(AboutMe);
            map3.putAll(ManagerUi);

            Map<String, String> commonMap = map2.entrySet().stream()
                    .filter(x -> map1.containsKey(x.getKey()))
                    .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
            Map<String,Map<String,String>> combine= new HashMap<>();
            combine.put("AboutMe",commonMap);
            return combine;

        }

return null;
    }
    public Map<String,String> compareEmpDirPermission(List<String> role)
    {

        Map<String, String> hrPrivileges= new HashMap<>();
        hrPrivileges.put("Read","Read");
        hrPrivileges.put("Add","Add");
        hrPrivileges.put("Delete","Delete");

        Map<String,String> adminPermission= new HashMap<>();
        adminPermission.put("Read","Read");
        adminPermission.put("Add","Add");
        adminPermission.put("Delete","Delete");

        Map<String,String> managerPermission= new HashMap<>();
        managerPermission.put("Read","Read");
        managerPermission.put("Add","Add");

        Map<String,String> emppermission= new HashMap<>();
        emppermission.put("Read","Read");

        Map<String,String> Emp_Permissions=new HashMap<>();
        Emp_Permissions.put("Read","Read");
        Emp_Permissions.put("Delete","Delete");
        Emp_Permissions.put("Add","Add");

        if (role.toString().equals("[ROLE_HR]") || role.toString().equals("[ROLE_Emp, ROLE_HR]") || role.toString().equals("[ROLE_Emp, ROLE_HR, ROLE_Manager]"))
        {
            Map<String, String> map1 = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            map1.putAll(hrPrivileges);
            map2.putAll(Emp_Permissions);

            Map<String, String> commonMap = map2.entrySet().stream()
                    .filter(x -> map1.containsKey(x.getKey()))
                    .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
            return commonMap;
        }
        else if (role.toString().equals("[ROLE_ADMIN]"))
        {
            Map<String, String> map1 = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            map1.putAll(adminPermission);
            map2.putAll(Emp_Permissions);

            Map<String, String> commonMap = map2.entrySet().stream()
                    .filter(x -> map1.containsKey(x.getKey()))
                    .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
            return commonMap;

        }
        else if (role.toString().equals("[ROLE_Manager]") || role.toString().equals("[ROLE_Emp, ROLE_Manager]") || role.toString().equals("[ROLE_Emp, ROLE_HR, ROLE_Manager]"))
        {
            Map<String, String> map1 = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            map1.putAll(managerPermission);
            map2.putAll(Emp_Permissions);

            Map<String, String> commonMap = map2.entrySet().stream()
                    .filter(x -> map1.containsKey(x.getKey()))
                    .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
            return commonMap;

        }
        else if (role.toString().equals("[ROLE_Emp]"))
        {
            Map<String, String> map1 = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            map1.putAll(emppermission);
            map2.putAll(Emp_Permissions);

            Map<String, String> commonMap = map2.entrySet().stream()
                    .filter(x -> map1.containsKey(x.getKey()))
                    .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
            return commonMap;
        }

        return null;
    }
















}
