package com.example.test.service.user;


import com.example.test.dal.Users;
import com.example.test.dto.ResultDto.ResultDto;
import com.example.test.dto.user.CreateUpdateUserDto;
import com.example.test.dto.user.LoginUserDto;
import com.example.test.dto.user.ResponseUserDto;
import com.example.test.dto.user.UpdateUserPasswordDto;
import com.example.test.enums.UserRole;
import com.example.test.mapper.UserMapper;
import com.example.test.repository.FavoriteRepository;
import com.example.test.repository.UserRepository;
import com.example.test.utils.FileUtil;
import com.example.test.utils.ImageFolderProperties;
import com.example.test.utils.ResultPagedDto;
import com.example.test.utils.ResultUtil;
import com.raika.customexception.exceptions.CustomException;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final FileUtil fileUtil;
    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);


    public UserService(UserRepository userRepository, FileUtil fileUtil) {
        this.userRepository = userRepository;
        this.fileUtil = fileUtil;
    }

    public ResultDto<ResponseUserDto> registerUser(CreateUpdateUserDto model) {
        try {
            var getUser = userRepository.findByUsername(model.getUsername());
            if (getUser.isPresent()) {
                throw new CustomException.BadRequest("Username already exists");
            } else {
                var user = userMapper.toEntity(model);
                user.setUserRole(UserRole.User);
                var result = userRepository.save(user);
                return ResultUtil.success(userMapper.toDto(result));
            }
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResultDto<ResponseUserDto> findById(UUID id) {
        try {
            var result = userRepository.findById(id).orElseThrow(
                    () -> new CustomException.BadRequest("User with id  not found"));
            return ResultUtil.success(userMapper.toDto(result));
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public UserRole getUserRoleById(UUID id) {
        try {
            var result = userRepository.findById(id);
            return result.map(Users::getUserRole).orElse(null);
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResultDto<ResultPagedDto<List<ResponseUserDto>>> getList(
            String username,
            int pageSize,
            int page,
            String host
    ) {
        try {
            Pageable pageable = PageRequest.of(page - 1, pageSize); // Assuming `page` is 1-based
            List<ResponseUserDto> user = userRepository
                    .findByUsernameContainingOrAll(username, pageable)
                    .stream()
                    .map(userMapper::toDto)
                    .toList();
            user.forEach(item ->
                    item.setBeas64String(host + "/" + fileUtil
                            .getImage(
                                    item
                                            .getId()
                                            .toString()
                                    , ImageFolderProperties.userFolder
                            )
                    )
            );

            if (pageSize >= 1 && page >= 1) {
                var totalPage = (long) Math.ceil((double) userRepository.count() / pageSize);
                return ResultUtil.success(new ResultPagedDto(page, pageSize, totalPage, user));
            } else {
                throw new CustomException.BadRequest("please enter pageSize and page or Above zero");
            }
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @Override
    public void registerAdmin() {
        try {
            var getUser = userRepository.findByUsername("admin");
            if (getUser.isEmpty()) {
                var user = new Users();
                user.setFullname("admin");
                user.setUsername("admin");
                user.setPassword("admin");
                user.setUserRole(UserRole.Admin);
                userRepository.save(user);
            }
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    public ResultDto<ResponseUserDto> changePassword(UUID id, UpdateUserPasswordDto model) {
        try {

            Users user = userRepository.findById(id).orElseThrow(
                    () -> new CustomException.BadRequest("User with id  not found", id)
            );
            //model for validation that time if I dose not ApiModelProperty
            if (!user.getPassword().equals(model.getOldPassword())) {
                throw new CustomException.BadRequest("Old password doesn't match password");
            } else {
                user.setPassword(model.getNewPassword());
                userRepository.save(user);
                return ResultUtil.success(userMapper.toDto(user));
            }
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResultDto<ResponseUserDto> login(LoginUserDto model) {
        try {
            Users users = userRepository.findByUsernameAndPassword(
                            model.getUsername(),
                            model.getPassword())
                    .orElseThrow(
                            () -> new CustomException.BadRequest("User not found")
                    );
            return ResultUtil.success(userMapper.toDto(users));
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResultDto<ResponseUserDto> create(CreateUpdateUserDto model) {
        try {
            var getUser = userRepository.findByUsername(model.getUsername());
            if (getUser.isPresent()) {
                throw new CustomException.BadRequest("Username already exists");
            } else {
                var user = userMapper.toEntity(model);
                user.setUserRole(UserRole.User);
                var result = userRepository.save(user);
                if (model.getBeas64String() != null && model.getBeas64String().toString() != "String") {
                    fileUtil.saveImage(model.getBeas64String(), user.getId().toString(), ImageFolderProperties.userFolder);
                }
                return ResultUtil.success(userMapper.toDto(result));
            }
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    public ResultDto<ResponseUserDto> updateProfile(UUID userId, CreateUpdateUserDto model) {
        try {
            var user = userRepository.findById(userId).orElseThrow(
                    () -> new CustomException.BadRequest("User not found")
            );
            var toEntity = userMapper.toEntity(model);
            var userSave = userRepository.save(toEntity);
            if (model.getBeas64String() != null) {
                fileUtil.saveImage(model.getBeas64String(), user.getId().toString(), ImageFolderProperties.userFolder);
            }
            return ResultUtil.success(userMapper.toDto(userSave));
        } catch (CustomException.NewException e) {
            throw new CustomException.NewException(e.getMessage(), e.getStatusCode());
        } catch (Exception e) {
            throw new CustomException.NewException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}


