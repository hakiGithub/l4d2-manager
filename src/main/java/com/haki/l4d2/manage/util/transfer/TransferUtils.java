package com.haki.l4d2.manage.util.transfer;

import com.haki.l4d2.manage.pojo.dto.L4d2MapDTO;
import com.haki.l4d2.manage.pojo.po.MapPO;
import com.haki.l4d2.manage.util.exception.IllegalException;
import org.springframework.beans.BeanUtils;

import java.util.Objects;
import java.util.function.Function;

/**
 * @Author:haki
 * @DATE:2019/5/12
 * @Description: 主要是用于复制等操作
 */
public class TransferUtils {

    /**
     * 这是地图的转换规则
     */
    public static Function<L4d2MapDTO,MapPO> TRANSFER_MAP = apply-> beanCopy(apply,new MapPO());


    /**
     * 进行对象COPY
     *
     * @param s   源对象
     * @param d   目标对象
     * @param <S>
     * @param <D>
     * @return
     */
    public static <S, D> D beanCopy(S s, D d) {
        if (Objects.isNull(s)) {
            throw new IllegalException("参数转换失败");
        }
        BeanUtils.copyProperties(s, d);
        return d;
    }
}
