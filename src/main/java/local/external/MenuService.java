
package local.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="MenuManage", url="${api.menu.url}")//,fallback = MenuServiceFallback.class)
public interface MenuService {

    @RequestMapping(method= RequestMethod.PUT, value="/menus/{menuId}", consumes = "application/json")
    public void orderRequest(@PathVariable("menuId") Long menuId, @RequestBody Menu menu);

}
