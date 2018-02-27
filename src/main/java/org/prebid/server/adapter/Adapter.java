package org.prebid.server.adapter;

import org.prebid.server.adapter.model.ExchangeCall;
import org.prebid.server.adapter.model.HttpRequest;
import org.prebid.server.exception.PreBidException;
import org.prebid.server.model.Bidder;
import org.prebid.server.model.BidderResult;
import org.prebid.server.model.PreBidRequestContext;
import org.prebid.server.model.response.Bid;

import java.util.List;

/**
 * Describes the behavior for {@link Adapter} implementations.
 * <p>
 * Used by {@link HttpConnector} while performing requests to exchanges and compose results.
 */
public interface Adapter {

    /**
     * Returns adapter's name.
     */
    String name();

    /**
     * Composes list of http request to submit to exchange.
     *
     * @throws PreBidException if error occurs while adUnitBids validation.
     */
    List<HttpRequest> makeHttpRequests(Bidder bidder, PreBidRequestContext preBidRequestContext) throws PreBidException;

    /**
     * Extracts bids from exchange response.
     *
     * @throws PreBidException if error occurs while bids validation.
     */
    List<Bid.BidBuilder> extractBids(Bidder bidder, ExchangeCall exchangeCall) throws PreBidException;

    /**
     * If true - {@link BidderResult} will contain bids if at least one valid bid exists, otherwise will contain error.
     * <p>
     * If false - {@link BidderResult} will contain error if at least one error occurs during processing.
     */
    boolean tolerateErrors();
}
