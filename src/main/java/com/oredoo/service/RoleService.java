//package com.oredoo.service;
//
//public class RoleService {
//
//}

package com.oredoo.service;

import com.oredoo.dto.request.RoleRequestDTO;
import com.oredoo.response.Response;

// from tag service
public interface RoleService {
    Response getAll();

    Response saveOrUpdate(RoleRequestDTO dto);

    Response getById(RoleRequestDTO dto);

    Response delete(RoleRequestDTO dto);
}

