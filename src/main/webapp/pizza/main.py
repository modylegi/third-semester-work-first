import os





def absoluteFilePaths(directory):
    for dirpath,_,filenames in os.walk(directory):
        for f in filenames:
            print(os.path.abspath(os.path.join(dirpath, f)))


absoluteFilePaths('C:/Users/user/Desktop/univer/semester-work-first/src/main/webapp/WEB-INF/view/pizza/storage')