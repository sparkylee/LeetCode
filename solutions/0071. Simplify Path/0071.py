class Solution(object):
    def simplifyPath(self, path):
        """
        :type path: str
        :rtype: str
        """
        pathList =  [x for x in path.split("/") if x != ''] ;
        pathStack = [];
        for item in pathList:
            if item == '.':
                continue;
            if item == "..":
                if  pathStack:
                    del pathStack[-1];
                continue
            pathStack.append(item);
        path  = "";
        if not pathStack:
            return "/";
        for item in pathStack:
            path += "/" + item;
        return path;