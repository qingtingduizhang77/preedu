package com.tware.config.service;

import com.tware.common.service.JDBCDaoImp;
import com.tware.config.entity.QSpResults;
import com.tware.config.entity.SpResults;
import com.tware.config.repository.SpResultsRepository;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import swallow.framework.exception.SwallowException;
import swallow.framework.service.BaseService;

@Service
public class SpResultsService extends BaseService<SpResultsRepository, SpResults> {

    @Autowired
    private JDBCDaoImp jdbcDaoImp;

    public SpResults getItems(String certNo, String year){
        QSpResults qsp = QSpResults.spResults;
        return super.getItem(query -> query.where(qsp.certNo.eq(certNo)
                .and(qsp.year.eq(year))));
    }

    public String updItems(SpResults item){
        String message = "";
        if (item.getCertNos().length > 0) {
            for (int i = 0;i<item.getCertNos().length;i++) {
                SpResults spResults = this.updItem(item, i);
                if(spResults == null)
                    message += item.getCertNos()[i]+"\n";
            }
        }
        if (StringUtils.isNotEmpty(message))
               message += "修正的申请不存在";
        return message;
    }

    private SpResults updItem(SpResults item, int index){
        SpResults spResults = this.getItems(item.getCertNos()[index],  item.getYear());
        if (spResults == null) {
            return null;
        }
        spResults.setStatus(item.getStatus());
        spResults.setRemark(item.getRemark());
        return super.updateItem(spResults);
    }

    public String getApplyYear(){
        StringBuffer sql = new StringBuffer("select config_value from sys_config where config_key = ?");
        return jdbcDaoImp.queryForObject(String.class, sql.toString(),new Object[]{"applyYear"});
    }
}
