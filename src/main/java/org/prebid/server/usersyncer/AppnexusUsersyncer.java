package org.prebid.server.usersyncer;

import org.prebid.server.bidder.BidderName;
import org.prebid.server.model.response.UsersyncInfo;
import org.prebid.server.util.HttpUtil;

import java.util.Objects;

public class AppnexusUsersyncer implements Usersyncer {

    private static final String NAME = BidderName.appnexus.name();

    private final UsersyncInfo usersyncInfo;

    public AppnexusUsersyncer(String usersyncUrl, String externalUrl) {
        usersyncInfo = createUsersyncInfo(Objects.requireNonNull(usersyncUrl), Objects.requireNonNull(externalUrl));
    }

    private static UsersyncInfo createUsersyncInfo(String usersyncUrl, String externalUrl) {
        final String redirectUri = HttpUtil.encodeUrl("%s/setuid?bidder=adnxs&uid=$UID", externalUrl);

        return UsersyncInfo.of(String.format("%s%s", usersyncUrl, redirectUri), "redirect", false);
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String cookieFamilyName() {
        return "adnxs";
    }

    @Override
    public UsersyncInfo usersyncInfo() {
        return usersyncInfo;
    }
}
