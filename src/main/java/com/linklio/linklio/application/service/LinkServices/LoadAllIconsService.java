package com.linklio.linklio.application.service.LinkServices;

import com.linklio.linklio.application.ports.out.linkPorts.LoadIconPort;
import com.linklio.linklio.domain.model.Icon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LoadAllIconsService {
    private final LoadIconPort loadIconPort;

    public List<Icon> loadAllIcons(){
        return loadIconPort.findAll();
    }
}
