var bar = "<div class=\"layui-collapse\">\n" +
    "            <div class=\"layui-colla-item\">\n" +
    "                <h2 class=\"layui-colla-title\">数据</h2>\n" +
    "                <div class=\"layui-colla-content layui-show\">\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">值</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <select name=\"xAxis\" lay-verify=\"required\">\n" +
    "                                <option value=\"\"></option>\n" +
    "                            </select>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">Y轴</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                             <select name=\"yAxis\" lay-verify=\"required\">\n" +
    "                                <option value=\"\"></option>\n" +
    "                            </select>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">系列</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <select name=\"series\" lay-verify=\"required\">\n" +
    "                                <option value=\"\"></option>\n" +
    "                            </select>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "            <div class=\"layui-colla-item\">\n" +
    "                <h2 class=\"layui-colla-title\">标题</h2>\n" +
    "                <div class=\"layui-colla-content layui-show\">\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">名称</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <input id=\"text\" name=\"text\" autocomplete=\"off\" class=\"layui-input\" type=\"text\">\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">副标题名称</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <input id=\"subtext\" name=\"subtext\" autocomplete=\"off\" class=\"layui-input\" type=\"text\">\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">对齐</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <select name=\"left\" id=\"left\" lay-verify=\"required\">\n" +
    "                                <option value=\"left\">居左</option>\n" +
    "                                <option value=\"center\">居中</option>\n" +
    "                                <option value=\"right\">居右</option>\n" +
    "                            </select>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "            <div class=\"layui-colla-item\">\n" +
    "                <h2 class=\"layui-colla-title\">图形</h2>\n" +
    "                <div class=\"layui-colla-content layui-show\">\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">背景色</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <input id=\"backgroundColor\" name=\"backgroundColor\" autocomplete=\"off\" class=\"layui-input\" type=\"text\">\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "            <div class=\"layui-colla-item\">\n" +
    "                <h2 class=\"layui-colla-title\">图例</h2>\n" +
    "                <div class=\"layui-colla-content layui-show\">\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">排列方式</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <select name=\"orient\" id=\"orient\">\n" +
    "                                <option value=\"horizontal\">水平</option>\n" +
    "                                <option value=\"vertical\">垂直</option>\n" +
    "                            </select>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">对齐</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <select name=\"legend_left\" id=\"legend_left\">\n" +
    "                                <option value=\"left\">居左</option>\n" +
    "                                <option value=\"center\">居中</option>\n" +
    "                                <option value=\"right\">居右</option>\n" +
    "                            </select>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">文字颜色</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <input id=\"color\" name=\"color\" autocomplete=\"off\" class=\"layui-input\" type=\"text\">\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "\n" +
    "        </div>";
var pie = " <div class=\"layui-collapse\">\n" +
    "            <div class=\"layui-colla-item\">\n" +
    "                <h2 class=\"layui-colla-title\">数据</h2>\n" +
    "                <div class=\"layui-colla-content layui-show\">\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">值</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <select name=\"xAxis\" lay-verify=\"required\">\n" +
    "                                <option value=\"\"></option>\n" +
    "                            </select>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">系列</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <select name=\"series\" lay-verify=\"required\">\n" +
    "                                <option value=\"\"></option>\n" +
    "                            </select>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "            <div class=\"layui-colla-item\">\n" +
    "                <h2 class=\"layui-colla-title\">标题</h2>\n" +
    "                <div class=\"layui-colla-content layui-show\">\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">名称</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <input id=\"text\" name=\"text\" autocomplete=\"off\" class=\"layui-input\" type=\"text\">\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">副标题名称</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <input id=\"subtext\" name=\"subtext\" autocomplete=\"off\" class=\"layui-input\" type=\"text\">\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">对齐</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <select name=\"left\" id=\"left\" lay-verify=\"required\">\n" +
    "                                <option value=\"left\">居左</option>\n" +
    "                                <option value=\"center\">居中</option>\n" +
    "                                <option value=\"right\">居右</option>\n" +
    "                            </select>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "            <div class=\"layui-colla-item\">\n" +
    "                <h2 class=\"layui-colla-title\">图形</h2>\n" +
    "                <div class=\"layui-colla-content layui-show\">\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">背景色</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <input id=\"backgroundColor\" name=\"backgroundColor\" autocomplete=\"off\" class=\"layui-input\" type=\"text\">\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "            <div class=\"layui-colla-item\">\n" +
    "                <h2 class=\"layui-colla-title\">图例</h2>\n" +
    "                <div class=\"layui-colla-content layui-show\">\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">排列方式</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <select name=\"orient\" id=\"orient\">\n" +
    "                                <option value=\"horizontal\">水平</option>\n" +
    "                                <option value=\"vertical\">垂直</option>\n" +
    "                            </select>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">对齐</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <select name=\"legend_left\" id=\"legend_left\">\n" +
    "                                <option value=\"left\">居左</option>\n" +
    "                                <option value=\"center\">居中</option>\n" +
    "                                <option value=\"right\">居右</option>\n" +
    "                            </select>\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                    <div class=\"layui-form-item\">\n" +
    "                        <label class=\"layui-form-label\">文字颜色</label>\n" +
    "                        <div class=\"layui-input-block\">\n" +
    "                            <input id=\"color\" name=\"color\" autocomplete=\"off\" class=\"layui-input\" type=\"text\">\n" +
    "                        </div>\n" +
    "                    </div>\n" +
    "                </div>\n" +
    "            </div>\n" +
    "\n" +
    "        </div>";