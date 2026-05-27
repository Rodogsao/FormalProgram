# Readme  
这里包含了该项目的注意事项  

## 1.分支管理
分支管理按照以下结构进行：

main  
└── dev  
├── module/resource  
│ ├── module/resource/upload  
│ ├── module/resource/download  
│ ├── module/resource/search  
│ ├── module/resource/minio  
│ └── module/resource/es  
│  
├── module/auth  
│ ├── module/auth/login  
│ ├── module/auth/jwt  
│ └── module/auth/rbac  
│  
├── module/project  
│ ├── module/project/approval  
│ └── module/project/export  


- `main` 是主分支  
- `dev` 是开发分支  
- `module/resource` 是模块分支  
- `module/resource/upload` 是模块开发的功能分支  

> **注意**：创建分支时请确认上一级的分支。