package structural.proxy;

/**
 * Title: 代理模式（简单的保护代理、引用计数代理）
 * Desc:
 * Created by Myth-Lab on 12/3/2019
 */
/*
某软件公司承接了某信息咨询公司的收费商务信息查询系统的开发任务，该系统的基本需求如下：
(1) 在进行商务信息查询之前用户需要通过身份验证，只有合法用户才能够使用该查询系统；(保护代理)
(2) 在进行商务信息查询时系统需要记录查询日志，以便根据查询次数收取查询费用。 （查询代理）
该软件公司开发人员已完成了商务信息查询模块的开发任务，现希望能够以一种松耦合的方式向原有系统增加身份验证和日志记录功能，
客户端代码可以无区别地对待原始的商务信息查询模块和增加新功能之后的商务信息查询模块，而且可能在将来还要在该信息查询模块中增加一些新的功能。
试使用代理模式设计并实现该收费商务信息查询系统。
 */
abstract class Retriever {
    abstract String doSearch(String id, String query);
}
class RealRetriever extends Retriever {

    @Override
    String doSearch(String id, String query) {
        return "Search results";
    }
}
// ****************************新功能***************
class ProxyRetriever extends Retriever {   // 1. 使用和Real Retriever 一样的接口，使得客户端可以无区别的对待扩展前后的模块
    Retriever retriever = new RealRetriever();  // 2.内部使用真实角色
    // 3. 使用真实角色做一些事情
    @Override
    String doSearch(String id, String query) {
        String searchResults = "No results";
        if (validate(id)) {   // 保护代理
            searchResults = retriever.doSearch(id, query);
            log(id);  // 引用计数代理
            // 缓冲代理
            // 将search results 保存起来，如果下次查询的时候，来了一个相同的query，直接在缓冲中取出来就行
        } else {
            searchResults = "No log in, invalidate User";
        }
        return searchResults;

    }
    private boolean validate(String id) {
        return ("1".equals(id));
    }
    void log(String id) {
        System.out.println(id + " search times + 1");
    }
}

public class SimpleProxyDemo {
    public static void main(String[] args) {
        Retriever retriever = new RealRetriever();
        String userId = "1";
        retriever.doSearch(userId, "User query");
        retriever = new ProxyRetriever();
        retriever.doSearch(userId, "User query");
    }

}
