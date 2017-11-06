package Helper;

import Beans.ReplyBean;
import Services.CarritoService;
import Services.LineadepedidoService;
import Services.PedidoService;
import Services.ProductoService;
import Services.TipousuarioService;
import Services.UsuarioService;
import javax.servlet.http.HttpServletRequest;

public class MappingHelper {

    public static ReplyBean executeMethodService(HttpServletRequest oRequest) throws Exception {
        String ob = oRequest.getParameter("ob");
        String op = oRequest.getParameter("op");
        ReplyBean oReplyBean = null;
        switch (ob) {
            case "usuario":
                UsuarioService oUsuarioService = new UsuarioService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oUsuarioService.get();
                        break;
                    case "set":
                        oReplyBean = oUsuarioService.set();
                        break;
                    case "remove":
                        oReplyBean = oUsuarioService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oUsuarioService.getpage();
                        break;
                    case "getcount":
                        oReplyBean = oUsuarioService.getcount();
                        break;
                    case "login":
                        oReplyBean = oUsuarioService.login();
                        break;
                    case "logout":
                        oReplyBean = oUsuarioService.logout();
                        break;
                    case "check":
                        oReplyBean = oUsuarioService.check();
                        break;
                    case "getpagextipousuario":
                        oReplyBean = oUsuarioService.getpagextipousuario();
                        break;
                    case "getcountxtipousuario":
                        oReplyBean = oUsuarioService.getcountxtiposuario();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation not found : Please contact your administrator");
                        break;
                }
                break;
            case "tipousuario":
                TipousuarioService oTipousuarioService = new TipousuarioService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oTipousuarioService.get();
                        break;
                    case "set":
                        oReplyBean = oTipousuarioService.set();
                        break;
                    case "remove":
                        oReplyBean = oTipousuarioService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oTipousuarioService.getpage();
                        break;
                    case "getcount":
                        oReplyBean = oTipousuarioService.getcount();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation not found : Please contact your administrator");
                        break;
                }
                break;
            case "pedido":
                PedidoService oPedidoService = new PedidoService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oPedidoService.get();
                        break;
                    case "set":
                        oReplyBean = oPedidoService.set();
                        break;
                    case "remove":
                        oReplyBean = oPedidoService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oPedidoService.getpage();
                        break;
                    case "getcount":
                        oReplyBean = oPedidoService.getcount();
                        break;
                    case "getpagexusuario":
                        oReplyBean = oPedidoService.getpagexusuario();
                        break;
                    case "getcountxusuario":
                        oReplyBean = oPedidoService.getcountxusuario();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation not found : Please contact your administrator");
                        break;
                }
                break;
            case "producto":
                ProductoService oProductoService = new ProductoService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oProductoService.get();
                        break;
                    case "set":
                        oReplyBean = oProductoService.set();
                        break;
                    case "remove":
                        oReplyBean = oProductoService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oProductoService.getpage();
                        break;
                    case "getcount":
                        oReplyBean = oProductoService.getcount();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation not found : Please contact your administrator");
                        break;
                }
                break;
            case "lineadepedido":
                LineadepedidoService oLineadepedidoService = new LineadepedidoService(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oLineadepedidoService.get();
                        break;
                    case "set":
                        oReplyBean = oLineadepedidoService.set();
                        break;
                    case "remove":
                        oReplyBean = oLineadepedidoService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oLineadepedidoService.getpage();
                        break;
                    case "getcount":
                        oReplyBean = oLineadepedidoService.getcount();
                        break;
                    case "getpagexpedido":
                        oReplyBean = oLineadepedidoService.getpagexpedido();
                        break;
                    case "getcountxpedido":
                        oReplyBean = oLineadepedidoService.getcountxpedido();
                        break;
                    case "getpagexproducto":
                        oReplyBean = oLineadepedidoService.getpagexproducto();
                        break;
                    case "getcountxproducto":
                        oReplyBean = oLineadepedidoService.getcountxproducto();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation not found : Please contact your administrator");
                        break;
                }
                break;
            case "carrito":
                CarritoService oCarritoService = new CarritoService(oRequest);
                switch (op) {
                    case "add":
                        oReplyBean = oCarritoService.add();
                        break;
                    case "remove":
                        oReplyBean = oCarritoService.remove();
                        break;
                    case "list":
                        oReplyBean = oCarritoService.list();
                        break;
                    case "buy":
                        oReplyBean = oCarritoService.buy();
                        break;
                    case "empty":
                        oReplyBean = oCarritoService.empty();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation not found : Please contact your administrator");
                        break;
                }
                break;

            default:
                oReplyBean = new ReplyBean(500, "Object not found : Please contact your administrator");
                break;
        }
        return oReplyBean;
    }
}
