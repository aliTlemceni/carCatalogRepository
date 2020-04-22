package com.tlemceni.restController;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tlemceni.entities.dto.RoleDto;
import com.tlemceni.security.model.Role;
import com.tlemceni.service.interf.RoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/roles/")
public class RoleController {
	
	private final Logger log = LoggerFactory.getLogger(RoleController.class);

    private static final String ENTITY_NAME = "role";

    private final RoleService roleService;

	public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    
    /**
     * POST  /users : Créer un nouveau user.
     *
     * @param user user à  créer
     * @return ResponseEntity avec status 201 (Created) et nouveau user dans le body, ou status 400 (Bad Request) si user posséde deja un ID
     * @throws URISyntaxException exception si URI est incorrecte
     */
    @PostMapping("addRole")
    public ResponseEntity<RoleDto> createRole(@Valid @RequestBody RoleDto roleDto) {
        RoleDto result = roleService.saveRole(roleDto);
        return new ResponseEntity<RoleDto>(result, HttpStatus.OK);
    }

    /**
     * PUT  /users : mettre à jour
     *
     * @param user user a mettre à jour
     * @return ResponseEntity avec status 200 (OK)  user à jour dans le body,
     * ou status 400 (Bad Request) si user non valide
     * ou avec status 500 (Internal Server Error) si user ne peut pas etre mis à jour
     */
    @PutMapping("")
    public ResponseEntity<RoleDto> updateRole(@Valid @RequestBody RoleDto roleDto) {

        RoleDto result = roleService.saveRole(roleDto);
        return new ResponseEntity<RoleDto>(result, HttpStatus.OK);
    }

    /**
     * GET  /users : recuperer tous(toutes) les users.
     *
     * @param pageable informations de pagination
     * @return ResponseEntity avec status 200 (OK) et la liste des users dans le body
     */
    @GetMapping()
    public ResponseEntity<List<RoleDto>> getAllRoles(@PageableDefault(size = 100, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        log.debug("REST request to get a page of Roles");
        Page<RoleDto> page = roleService.findAll(pageable);
        return new ResponseEntity<List<RoleDto>>(page.getContent(), HttpStatus.OK);
    }

    /**
     * GET  /users/:id : récupérer le/la user par "id".
     *
     * @param id l'id du user a recuperer
     * @return ResponseEntity avec status 200 (OK) et user dans le body, oubien avec status 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRole(@PathVariable Long id) {
        log.debug("REST request to get Role : {}", id);
        Optional<RoleDto> role = roleService.findOne(id);
        if(role.isPresent()) {
            return new ResponseEntity<RoleDto>(role.get(),HttpStatus.OK);
        } else
            return new ResponseEntity<RoleDto>(new RoleDto(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable("id") Long id){
            roleService.delete(id);

    }

}
