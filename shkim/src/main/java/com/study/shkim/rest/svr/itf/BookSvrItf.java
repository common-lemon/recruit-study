package com.study.shkim.rest.svr.itf;

import com.study.shkim.rest.dto.ReqBookParams;
import com.study.shkim.rest.dto.ResBook;

public interface BookSvrItf {

    ResBook list(ReqBookParams reqBookParams);

    ResBook save(ReqBookParams reqBookParams);

    ResBook delete(ReqBookParams reqBookParams);

    ResBook detail(ReqBookParams reqBookParams);

}
