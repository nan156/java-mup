package com.java.pms.dispord.controller;

import com.java.commonutils.api.APICODE;
import com.java.pms.dispord.entity.DispOrd;
import com.java.pms.dispord.service.DispOrdService;
import com.java.pms.dispord.vo.DispOrdQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "调度指令库管理 - 控制层")
@RestController
@RequestMapping("/pms/dispord")
public class DispOrdController {

    @Autowired
    private DispOrdService dispOrdService;
    /**
     * 查询所有调度指令库
     * @return
     */
    @ApiOperation(value = "查询所有调度指令库")
    @GetMapping
    public APICODE findAll(){
        // 查询
        List<DispOrd> dispOrdList = dispOrdService.findAll();
        int i = 1/0;
        return APICODE.OK().data("itmes", dispOrdList);
    }

    @ApiOperation(value = "带条件的分页查询")
    @PostMapping("{pageNo}/{pageSize}")
    public APICODE findPageDispOrd(DispOrdQuery dispOrdQuery,
                                   @PathVariable(name = "pageNo") int pageNo,
                                   @PathVariable(name = "pageSize") int pageSize){
        // 查询
        Page<DispOrd> page = dispOrdService.findPageDispOrd(dispOrdQuery, pageNo, pageSize);
        Long totalElements = page.getTotalElements();
        List<DispOrd> dispOrdList = page.getContent();
        return APICODE.OK().data("total", totalElements).data("items", dispOrdList);
    }

    @ApiOperation(value = "创建调度指令库管理")
    @PostMapping("saveDispOrd")
    public APICODE saveDispOrd(@RequestBody DispOrd dispOrd) {
        dispOrdService.saveOrUpdate(dispOrd);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据ID获取调度指令库")
    @GetMapping("{dispOrdId}")
    public APICODE getDispOrdById(@PathVariable(name = "dispOrdId") String dispOrdId) {
        DispOrd dispOrd = dispOrdService.getById(dispOrdId);
        return APICODE.OK().data("dispOrd", dispOrd);
    }

    @ApiOperation(value = "修改调度指令库")
    @PutMapping("updateDispOrd")
    public APICODE updateDispOrd(@RequestBody DispOrd dispOrd) {
        dispOrdService.saveOrUpdate(dispOrd);
        return APICODE.OK();
    }

    @ApiOperation(value = "根据ID删除调度指令库")
    @DeleteMapping("{dispOrdId}")
    public APICODE deleteDispOrdById(@PathVariable(name = "dispOrdId") String dispOrdId) {
        dispOrdService.removeById(dispOrdId);
        return APICODE.OK();
     }

}
