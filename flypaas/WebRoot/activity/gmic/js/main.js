/*global jQuery */

var TNW = {
    isMobile: /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent),

    core: {
        emailSignup: function (e) {
            'use strict';
            e.preventDefault();

            var $form = $(this),
                data = $form.serialize(),
                email = $form.find('input[name="email"]').first().val(),
                xhr;

            if (validateEmail(email)) {
                // Submit data
                xhr = $.post($form.attr('action'), data, 'json');
                $('button[type="submit"]', $form).attr('disabled', true);

                // Send subscriber to MailChimp
                $.when(xhr).then(function (data) {
                    if (data.success) {
                        alert('Thank you!');
                        $('button[type="submit"]', $form).removeAttr('disabled');
                    } else {
                        alert('Something failed. Please try again.');
                    }
                });
            } else {
                alert('Invalid email address. Please try again.');
            }
        }
    },

    nav: {
        duration: 300,

        init: function () {
            'use strict';
            $('#nav-toggle').on('click', TNW.nav.onToggle);
            $('#menu .has-sub > a').on('click', TNW.nav.onExpand);

            $(document).on('mediaquerychange', function (e, mq) {
                if (['m', 'l', 'xl'].indexOf(mq) !== -1) {
                    // Remove expanded state when not on mobile/tablet
                    $('#menu').find('li').removeClass('expanded');
                }
            });

            $(window).on('scroll', function () {
                $('#top')[($(window).scrollTop() > $(window).height() && !TNW.isMobile) ? 'addClass' : 'removeClass']('shrink');
            });
        },

        initScroll: function () {
            'use strict';
            var menuHeight = $('#menu').outerHeight(),
                topHeight = $('#top').outerHeight(),
                viewportHeight = $(window).height();

            if (topHeight > viewportHeight) {
                $('#menu').addClass('scroll').height(viewportHeight - (topHeight - menuHeight));
            } else {
                $('#menu').removeClass('scroll').height('auto');
            }
        },

        onExpand: function (e) {
            'use strict';

            e.preventDefault();
            var $li = $(this).closest('li'),
                $ul = $(this).siblings('ul').first(),
                isExpanded = $ul.is(':visible');

            console.log($(document).getMediaQuery());

            if (['xs', 's', 'm'].indexOf($(document).getMediaQuery()) !== -1) {
                // Collapse previous
                $li.siblings('.has-sub.expanded').removeClass('expanded').children('ul').slideUp(TNW.nav.duration, TNW.nav.initScroll);

                // Expand current
                $li[isExpanded ? 'removeClass' : 'addClass']('expanded');
                $ul[isExpanded ? 'slideUp' : 'slideDown'](TNW.nav.duration, TNW.nav.initScroll);
            }
        },

        onToggle: function (e) {
            'use strict';
            e.preventDefault();
            $(this).toggleClass('active');
            $('#menu')[$(this).is('.active') ? 'slideDown' : 'slideUp'](TNW.nav.duration, function () {
                TNW.nav.initScroll();

                // Collapse all sub-menus
                if ($(this).not(':visible')) {
                    $(this).find('.has-sub').removeClass('expanded').find('ul').hide();
                }
            });
        }
    }
};


(function ($) {
    'use strict';

    function aaa(){
         var elems = [],
            scrollTop = $(document).scrollTop(),
            viewportHeight = window.screen.height;

        $.each($.cache, function () {
            if (this.events && this.events.inview) {
                elems.push(this.handle.elem);
            }
        });

        if (elems.length) {
            $(elems).each(function () {
                var $this = $(this),
                    height = $this.height(),
                    top = $this.offset().top,
                    inview = $this.data('inview') || false;

                if ((scrollTop + viewportHeight) < top || scrollTop > (top + height)) {
                    if (inview) {
                        $this.data('inview', false);
                        $this.trigger('inview', [false]);
                    }
                } else if (scrollTop < (top + height)) {
                    if (!inview) {
                        $this.data('inview', true);
                        $this.data('inview-data', {height: height, top: top, viewportHeight: viewportHeight});
                        $this.trigger('inview', [true]);
                    }
                }
            });
        }
    }

    $(window).on('scroll', function () {

        aaa();
    });

    aaa();

}(jQuery));

/* **********************************************
     Begin jquery.mediaquerychange.js
********************************************** */

/*global jQuery */

/*
 * jquery.mediaquerychange.js
 * https://github.com/oscaralexander/mediaquerychange
 * Copyright (c) 2013, The Next Web - Alexander Griffioen <alexander@thenextweb.com>
 *
 * License: GPL v3
 */

(function ($) {
    'use strict';

    var currentMediaQuery = null;

    function getMediaQuery() {
        // Read content attribute of body::after pseudo-selector
        var mediaQuery = window.getComputedStyle(document.body, ':after').getPropertyValue('content');

        // Strip quotes (IE)
        if (mediaQuery.charAt(0) === '"') {
            mediaQuery = mediaQuery.substring(1, mediaQuery.length - 1);
        }

        return mediaQuery;
    }

    function onResize() {
        var mediaQuery = getMediaQuery();

        if (mediaQuery !== currentMediaQuery) {
            // Fire event if media query has changed
            $(document).trigger('mediaquerychange', [mediaQuery]);

            // Update current media query
            currentMediaQuery = mediaQuery;
        }
    }

    $.fn.getMediaQuery = function () {
        return getMediaQuery() || null;
    };

    $(function () {
        $(window).on('resize', onResize).trigger('resize');
    });
}(jQuery));

/* **********************************************
     Begin jquery.tnwcountdown.js
********************************************** */

/*global console, jQuery */

/*!
 * TNW Countdown
 * Copyright (c) The Next Web, 2013
 * @author Alexander Griffioen <alexander@thenextweb.com>
 * @version 1.0
 */
(function ($) {
    'use strict';

    var defaults = {},
        pluginName = 'tnwCountdown';

    function TNWCountdown(element, options) {
        this.element = element;
        this.$element = $(element);
        this.options = $.extend({}, defaults, options);
        this.init();
    }

    TNWCountdown.prototype = {
        init: function () {
            var dateString = this.$element.attr('data-datestring'),
                that = this;

            if (!dateString) {
                console.log('jQuery TNWCountdown: Element does not have a data-datestring attribute:');
                console.log(this.element);
                return;
            }

            this.dateTo = new Date(dateString);

            if (this.dateTo.toString() === 'Invalid Date') {
                console.log('jQuery TNWCountdown: Element has an invalid data-datestring attribute:');
                console.log(this.element);
                return;
            }

            this.interval = setInterval(function () { that.tick(); }, 1000);
            this.tick();
        },

        tick: function () {
            var dateNow,
                daysLeft,
                diff,
                hoursLeft,
                minutesLeft,
                secondsLeft,
                weeksLeft,
                MS_PER_MINUTE = 1000 * 60,
                MS_PER_HOUR = 1000 * 60 * 60,
                MS_PER_DAY = 1000 * 60 * 60 * 24,
                MS_PER_WEEK = 1000 * 60 * 60 * 24 * 7;

            dateNow = new Date();
            diff = this.dateTo.getTime() - dateNow.getTime();
            diff = (diff < 0) ? 0 : diff;

            // Update weeks
            weeksLeft = Math.floor((diff / (60 * 60 * 24 * 7)) / 1000);
            diff -= (weeksLeft * MS_PER_WEEK);
            this.$element.find('.weeks').text(String('0' + weeksLeft).substr(-2));

            // Update days
            daysLeft = Math.floor((diff / (60 * 60 * 24)) / 1000);
            diff -= (daysLeft * MS_PER_DAY);
            this.$element.find('.days').text(String('0' + daysLeft).substr(-2));

            // Update hours
            hoursLeft = Math.floor((diff / (60 * 60)) / 1000);
            diff -= (hoursLeft * MS_PER_HOUR);
            this.$element.find('.hours').text(String('0' + hoursLeft).substr(-2));

            // Update minutes
            minutesLeft = Math.floor((diff / 60) / 1000);
            diff -= (minutesLeft * MS_PER_MINUTE);
            this.$element.find('.minutes').text(String('0' + minutesLeft).substr(-2));

            // Update seconds
            secondsLeft = Math.floor(diff / 1000);
            this.$element.find('.seconds').text(String('0' + secondsLeft).substr(-2));
        }
    };

    $.fn[pluginName] = function (options) {
        return this.each(function () {
            if (!$.data(this, pluginName)) {
                $.data(this, pluginName, new TNWCountdown(this, options));
            }
        });
    };
}(jQuery));

/* **********************************************
     Begin jquery.tnwcycler.js
********************************************** */

/*globals jQuery */

/*!
 * TNW Cycler
 * Copyright (c) The Next Web, 2013
 * @author Alexander Griffioen <alexander@thenextweb.com>
 * @version 1.0
 */
(function ($) {
    'use strict';

    var defaults = {
            classIn: 'in',
            classOut: 'out',
            crossfade: true,
            interval: 6000,
            repeat: true
        },
        pluginName = 'tnwCycler';

    function TNWCycler(element, options) {
        this.$element = $(element);
        this.options = $.extend({}, defaults, options);
        this.init();
    }

    TNWCycler.prototype = {
        init: function () {
            var height = 0,
                that = this;

            this.currentIndex = 0;
            this.counter = 0;

            if (this.$element.children().length) {
                this.instances = this.$element.children().clone();

                this.$element.children().each(function () {
                    height = Math.max($(this).height(), height);
                });

                this.$element.children().remove();
                this.$element.height(height);

                if (this.instances.length > 1) {
                    this.interval = setInterval(function () { that.run(); }, this.options.interval);
                }

                this.run();
            }
        },

        run: function () {
            var $instance = $(this.instances[this.currentIndex]),
                that = this;

            if (this.options.crossfade) {
                if (this.$element.children().length) {
                    this.$element.children().first().one('transitionend msTransitionEnd webkitTransitionEnd otransitionend oTransitionEnd', function () {
                        $(this).remove();
                        that.$element.append($instance);
                        that.show();
                    }).addClass(this.options.classOut);
                } else {
                    $instance.appendTo(this.$element);
                    that.show();
                }
            } else {
                this.$element.children().one('transitionend msTransitionEnd webkitTransitionEnd otransitionend oTransitionEnd', function () {
                    $(this).remove();
                }).addClass(this.options.classOut);

                $instance.appendTo(this.$element);
                that.show();
            }

            this.currentIndex = this.currentIndex + 1;

            if (this.currentIndex === this.instances.length) {
                if (this.options.repeat) {
                    this.currentIndex = 0;
                } else {
                    this.stop();
                }
            }
        },

        show: function () {
            var that = this;

            setTimeout(function () {
                that.$element.children().last().addClass(that.options.classIn);
            }, 1);
        },

        stop: function () {
            if (this.interval) {
                clearInterval(this.interval);
            }
        }
    };

    $.fn[pluginName] = function (options) {
        return this.each(function () {
            if (!$.data(this, pluginName)) {
                $.data(this, pluginName, new TNWCycler(this, options));
            } else {
                if (typeof options === 'string') {
                    try {
                        $.data(this, pluginName)[options]();
                    } catch (ignore) {}
                }
            }
        });
    };
}(jQuery));

/* **********************************************
     Begin jquery.tnwgallery.js
********************************************** */

/*globals jQuery */

/*!
 * TNW Tabs
 * Copyright (c) The Next Web, 2014
 * @author Alexander Griffioen <alexander@thenextweb.com>
 * @version 1.0
 */
(function ($) {
    'use strict';

    var defaults = {
            inertia: 0.02
        },
        pluginName = 'tnwGallery';

    function TNWGallery(element, options) {
        this.$element = $(element);
        this.element = element;
        this.options = $.extend({}, defaults, options);
        this.init();
    }

    TNWGallery.prototype = {
        $ul: null,
        interval: null,
        numItems: 0,
        stop: true,
        viewportWidth: $(window).width(),
        width: 0,
        xCurrent: 0,
        xTo: 0,

        init: function () {
            this.$ul = this.$element.children('ul').first();
            this.numItems = this.$ul.children().length;
            $(window).on('resize', {scope: this}, this.onResize).trigger('resize');

            if(!TNW.isMobile) this.initMouseEvents();
        },

        initMouseEvents: function () {
            this.$element.on('mouseenter', {scope: this}, this.onMouseEnter);
            this.$element.on('mouseleave', {scope: this}, this.onMouseLeave);
        },

        move: function () {
            this.xCurrent += ((this.xTo - this.xCurrent) * this.options.inertia);
            this.position(this.xCurrent);

            if (this.stop && (Math.abs(this.xTo - this.xCurrent) < 1)) {
                if (this.interval) {
                    clearInterval(this.interval);
                }
            }
        },

        onResize: function (e) {
            var that = e.data.scope;

            that.viewportWidth = $(window).width();
            that.width = 0;

            that.$ul.children().each(function () {
                that.width += $(this).outerWidth(true);
            });

            that.$ul.width(that.width);
            that.width += parseInt(that.$ul.css('padding-left'), 10) + parseInt(that.$ul.css('padding-right'), 10);
            that.position((that.viewportWidth - that.width) * 0.5);
        },

        onMouseEnter: function (e) {
            var that = e.data.scope;

            if (that.width > that.viewportWidth) {
                that.$element.on('mousemove', {scope: that}, that.onMouseMove);
                that.stop = false;
                that.interval = setInterval(function () {
                    that.move();
                }, 0);
            }
        },

        onMouseLeave: function (e) {
            var that = e.data.scope;

            if (that.width > that.viewportWidth) {
                that.$element.off('mousemove');
                that.stop = true;
            }
        },

        onMouseMove: function (e) {
            var that = e.data.scope;
            that.xTo = (that.viewportWidth - that.width) * (e.pageX / that.viewportWidth);
        },

        position: function (x) {
            if ($('html').hasClass('touch')) {
                this.$element.scrollLeft(-x);
            } else {
                this.$ul.css({
                    '-webkit-transform': 'translateX(' + x + 'px)',
                    '-moz-transform': 'translateX(' + x + 'px)',
                    '-ms-transform': 'translateX(' + x + 'px)',
                    'transform': 'translateX(' + x + 'px)'
                });
            }
        }
    };

    $.fn[pluginName] = function (options) {
        return this.each(function () {
            if (!$.data(this, pluginName)) {
                $.data(this, pluginName, new TNWGallery(this, options));
            }
        });
    };
}(jQuery));

/* **********************************************
     Begin jquery.tnwparallax.js
********************************************** */

/*global jQuery */

/*!
 * TNW Parallax
 * Copyright (c) The Next Web, 2013
 * @author Alexander Griffioen <alexander@thenextweb.com>
 * @version 1.0
 */
(function ($) {
    'use strict';

    var defaults = {
            maxHeight: 1.5,
            offsetTop: 66
        },
        pluginName = 'tnwParallax';

    function TNWParallax(element, options) {
        this.$element = $(element);
        this.element = element;
        this.options = $.extend({}, defaults, options);
        this.init();
    }

    TNWParallax.prototype = {
        $layers: [],
        height: 0,
        numLayers: 0,

        init: function () {
            // Scale layers
            this.$layers = this.$element.children();
            this.increment = (this.options.maxHeight - 1) / this.$layers.length;
            this.numLayers = this.$layers.length;

            // Init visuals
            this.$layers.each(function () {
                var repeat, size;

                if ($(this).hasClass('visual')) {
                    if ($(this).attr('data-parallax-src')) {
                        repeat = $(this).attr('data-parallax-repeat') || 'auto';
                        size = $(this).attr('data-parallax-repeat') ? 'auto' : 'cover';

                        $(this).css({
                            backgroundImage: 'url(' + $(this).attr('data-parallax-src') + ')',
                            backgroundRepeat: repeat,
                            backgroundSize: size
                        });
                    }
                }
            });

            // Toggle .inview class
            this.$element.on('inview', function (e, inview) {
                $(e.currentTarget)[inview ? 'addClass' : 'removeClass']('inview');
            });

            // Set resize handler
            $(window).on('resize', {scope: this}, this.onResize).trigger('resize');
            // this.onResize({data: {scope: this}});

            // Only do parallax stuff on desktop devices
            if (!$('html').hasClass('touch')) {
                $(window).on('scroll', {scope: this}, this.onScroll).trigger('scroll');
            }
        },

        onResize: function (e) {
            var that = e.data.scope;
            that.height = $(window).height();

            // Set height
            if (that.options.height) {
                if (that.options.height < 1) {
                    that.height = $(window).height() * that.options.height;
                } else {
                    that.height = that.options.height;
                }
            }

            // Scale container
            that.$element.height(that.height);

            // Scale layers
            $(that.$layers.get().reverse()).each(function (i) {
                var $content,
                    $contentInner,
                    contentHeight,
                    layerHeight;

                layerHeight = Math.floor(100 + (100 * (that.increment * i)));
                $(this).height(layerHeight + '%');

                if ($(this).hasClass('content')) {
                    $content = $(this);
                    $contentInner = $content.children('.inner').first();

                    if ($contentInner.length) {
                        contentHeight = $contentInner.outerHeight();

                        if ((contentHeight + that.options.offsetTop) > that.height) {
                            that.height = contentHeight + that.options.offsetTop;
                            that.$element.height(that.height);
                            $content.height('100%');
                            $contentInner.css({'margin-top': that.options.offsetTop});
                        } else {
                            $contentInner.css({'margin-top': ((($content.height() - that.options.offsetTop) - contentHeight) * 0.5) + that.options.offsetTop});
                        }
                    }
                }
            });
        },

        onScroll: function (e) {
            var data,
                multiplier,
                that = e.data.scope,
                translateY;

            if (that.$element.hasClass('inview')) {
                data = that.$element.data('inview-data');
                multiplier = $(document).scrollTop() / (data.top + data.height);

                that.$element.children().each(function () {
                    if (that.options.invert) {
                        multiplier = -multiplier;
                    }

                    translateY = Math.round((that.height - $(this).height()) * multiplier);

                    $(this).css({
                        '-webkit-transform': 'translate(0, ' + translateY + 'px)',
                        '-moz-transform': 'translate(0, ' + translateY + 'px)',
                        '-ms-transform': 'translate(0, ' + translateY + 'px)',
                        'transform': 'translate(0, ' + translateY + 'px)'
                    });
                });
            }
        }
    };

    $.fn[pluginName] = function (options) {
        return this.each(function () {
            if (!$.data(this, pluginName)) {
                $.data(this, pluginName, new TNWParallax(this, options));
            }
        });
    };
}(jQuery));

/* **********************************************
     Begin jquery.tnwtabs.js
********************************************** */

/*globals jQuery */

/*!
 * TNW Tabs
 * Copyright (c) The Next Web, 2014
 * @author Alexander Griffioen <alexander@thenextweb.com>
 * @version 1.0
 */
(function ($) {
    'use strict';

    var defaults = {
            activeTabId: null,
            classActive: 'active'
        },
        pluginName = 'tnwTabs';

    function TNWTabs(element, options) {
        this.$element = $(element);
        this.options = $.extend({}, defaults, options);
        this.init();
    }

    TNWTabs.prototype = {
        activeTabId: null,
        tabIds: [],

        init: function () {
            var hash,
                that = this;

            this.$element.children().each(function () {
                var $this = $(this),
                    id;

                id = $this.attr('href').substr(1);
                that.tabIds.push(id);
                $('#' + id).hide();

                if ($this.hasClass(that.options.classActive)) {
                    $this.removeClass(that.options.classActive);
                    that.activeTabId = id;
                }
            });

            // Set by URL hash
            if (window.location.hash !== '') {
                hash = window.location.hash.substr(1);

                if ($.inArray(hash, this.tabIds)) {
                    this.activeTabId = hash;
                }
            }

            if (!this.activeTabId) {
                this.activeTabId = this.tabIds[0];
            }

            // Set current tab content
            $('.tabs a[href="#' + this.activeTabId + '"]').addClass(this.options.classActive);
            $('#' + this.activeTabId).addClass(this.options.classActive).show();

            // Init click handlers
            this.$element.children().on('click', function (e) {
                var $this = $(this),
                    id = $this.attr('href').substr(1);

                // Set active tab
                that.$element.children().removeClass(that.options.classActive);
                $this.addClass(that.options.classActive);

                // Set active tab content
                $.each(that.tabIds, function (i, tabId) {
                    $('#' + tabId).hide().removeClass(that.options.classActive);
                });

                $('#' + id).show().addClass(that.options.classActive);
                e.preventDefault();
            });
        }
    };

    $.fn[pluginName] = function (options) {
        return this.each(function () {
            if (!$.data(this, pluginName)) {
                $.data(this, pluginName, new TNWTabs(this, options));
            }
        });
    };
}(jQuery));

/* **********************************************
     Begin main.js
********************************************** */

/*global $ */

function validateEmail(email) {
    'use strict';
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}





/* **********************************************
     Begin tnwschedule.js
********************************************** */

/*globals $, TNW */

TNW.schedule = {
    init: function () {
        'use strict';
        if ($('#timetable').length) {
            this.initHighlight();
            this.initMobileNav();
        }

        this.initModal();
    },

    initHighlight: function () {
        'use strict';
        var $highlight = $('#timetable .highlight'),
            $time,
            date,
            offset,
            time = new Date();

        // Format current date: YYYYMMDD
        date = '' + time.getFullYear() + ('0' + (time.getMonth() + 1)).slice(-2) + ('0' + time.getDate()).slice(-2);

        if (date === $highlight.attr('data-date')) {
            // Ahhh! So many parseInts!
            time = parseInt(time.getHours() + ('0' + time.getMinutes()).slice(-2), 10) / 10;
            time = parseInt((parseInt(time, 10) / 10) * 100, 10);
            $time = $('li[data-time="' +  time + '"]').first();

            // Position highlight bar
            if ($time.length) {
                offset = $time.offset().top - $('#timetable').offset().top;
                $highlight.css({top: offset}).show();
            }

            // Highlight active blocks
            $('#rooms li').each(function () {
                var timeEnd = parseInt($(this).attr('data-time-end'), 10),
                    timeStart = parseInt($(this).attr('data-time-start'), 10);

                if (timeStart <= time && timeEnd > time) {
                    $(this).addClass('active');
                }
            });
        }
    },

    initMobileNav: function () {
        'use strict';
        var $timetable = $('#timetable'),
            $roomControls = $timetable.find('#room-controls'),
            roomControlsHeight = $roomControls.height(),
            timetableTop = $timetable.offset().top,
            timetableHeight = $timetable.outerHeight(),
            topOffset = 80,
            viewportHeight = $(window).height();

        // Toggle room control visibility
        $(window).on('scroll', function () {
            var scrollTop = $(document).scrollTop();

            if (timetableTop - scrollTop < viewportHeight - topOffset) {
                $roomControls.addClass('show');
            } else {
                $roomControls.removeClass('show');
            }

            if (scrollTop + viewportHeight > timetableTop + timetableHeight + roomControlsHeight) {
                $roomControls.removeClass('show');
            }
        });

        // Room selection handler
        $('#room-controls a').on('touch click', function (e) {
            var room = $(this).attr('data-room');

            // Activate current tab
            $('#room-controls li').removeClass('active');
            $(this).closest('li').addClass('active');

            // Show room
            $('#rooms-inner').removeClass('show-red show-blue show-green').addClass('show-' + room);

            e.preventDefault();
        });
    },

    closeModal: function () {
        'use strict';
        $('#schedule-modal').on('webkitTransitionEnd mozTransitionEnd msTransitionEnd transitionend', function () {
            $(this).off('webkitTransitionEnd mozTransitionEnd msTransitionEnd transitionend').removeClass('loaded ready remove').html('');
            $('body').removeClass('modal');
        }).addClass('remove');
    },

    initModal: function () {
        'use strict';
        $('body').on('touch click', '.schedule-item', TNW.schedule.openModal);
    },

    openModal: function () {
        'use strict';
        var id = $(this).attr('data-id');

        if ($(this).is('.break')) {
            return;
        }

        if ($('#schedule-modal').hasClass('ready')) {
            // Another modal is still open, prep for re-use
            $('#schedule-modal').removeClass('loaded');
        } else {
            // Display overlay
            $('#schedule-modal').addClass('ready').on('click', function (e) {
                var $el = $(e.target);

                if ($el.is('#schedule-modal') || $el.is('#schedule-modal .close')) {
                    e.preventDefault();
                    e.stopPropagation();
                    $('#schedule-modal').off('click');
                    TNW.schedule.closeModal();
                }
            });

            setTimeout(function () {
                $('body').addClass('modal');
            }, 1);
        }

        $('#schedule-modal').html('').load('/conference/europe/schedule/info/' + id, function (response, status) {
            if (status === 'success') {
                setTimeout(function () {
                    $('#schedule-modal').addClass('loaded');
                }, 1);
            }
        });
    }
};


$(function () {
    'use strict';



    // Init email signup
   // $('.email-signup').on('submit', TNW.core.emailSignup);

    // Init video lightbox
   // $('.video').magnificPopup({mainClass: 'mfp-fade', type: 'iframe'});

    // Init image gallery lightbox





    // Init Boost scroll
    // if ($('.red-bar').length) {
    //     $(window).on('scroll', function () {
    //         $('.red-bar').css({'background-position': $(window).scrollTop() + 'px 0px'});
    //     });
    // }

    // $('#home-cycler').tnwCycler({crossfade: false, interval: 1500, repeat: false});
});

$(function () {
    'use strict';
    TNW.schedule.init();
    TNW.nav.init();

     $('.tabs').tnwTabs();


  //  if(!TNW.isMobile) {
        $('.tnw-gallery').tnwGallery();
  //  }

    // Init parallax sections
    $('#home').tnwParallax();
    $('#header-sub').tnwParallax({height: 0.5});

    $('#pop-video').magnificPopup({
        type: 'iframe'
    });

    $('.img-list .img').magnificPopup({
        type: 'image',
        gallery: {
            enabled: true,
            preload: [1, 1]
        }
    });

     $('.img-list .v').magnificPopup({
        type: 'iframe'
    });

       $('.tnw-gallery a').magnificPopup({
        type: 'image',
        gallery: {
            enabled: true,
            preload: [1, 1]
        }
    });




    if(TNW.isMobile) {
        $('html').addClass('touch');
    }
});