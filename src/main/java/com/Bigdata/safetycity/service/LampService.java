package com.Bigdata.safetycity.service;

import com.Bigdata.safetycity.model.Count;
import com.Bigdata.safetycity.model.datas.StreetLamp;
import com.Bigdata.safetycity.repository.LampRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LampService {
    @Autowired
    private LampRepository lampRepository;

    @Transactional(readOnly = true)
    public Page<StreetLamp> getLampsByArea(Pageable pageable, String area){
        return lampRepository.findStreetLampByManagementOrg(pageable, area);
    }

    @Transactional(readOnly = true)
    public List<Count> getTopLampCount(){
        List<Count> lamps = lampRepository.findTop5Lamp();
        return lamps;
    }
}
