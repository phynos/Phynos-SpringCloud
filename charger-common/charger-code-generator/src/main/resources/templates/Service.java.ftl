package ${base_package}.core.service.${module_group};

import ${base_package}.core.json.JsonResult;
import ${base_package}.core.params.BaseParam;
import ${base_package}.dao.model.${model};

import java.util.List;

/**
* @Author: ${author}
* @Date: ${date}
**/
public interface ${model}Service {

    JsonResult pageList(BaseParam param);

    JsonResult list();

    JsonResult add(${model} param);

    JsonResult del(List<${primary_id_type}> ids);

    JsonResult mod(${model} param);

    JsonResult detail(${primary_id_type} id);

}