package com.liferay.example.site.initializer;

import com.liferay.commerce.initializer.util.JournalArticleImporter;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.site.exception.InitializationException;
import com.liferay.site.initializer.SiteInitializer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.servlet.ServletContext;
import java.util.Locale;
import java.util.ResourceBundle;

@Component(
        immediate = true,
        property = "site.initializer.key=" + PrezzSiteInitializer.KEY,
        service = SiteInitializer.class
)
public class PrezzSiteInitializer implements SiteInitializer {

    public static final String KEY = "prezz-initializer";

    @Override
    public String getDescription(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
                "content.Language", locale, getClass());

        return LanguageUtil.get(resourceBundle, "prezz-description");
    }

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public String getName(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
                "content.Language", locale, getClass());

        return LanguageUtil.get(resourceBundle, "prezz-name");
    }

    @Override
    public String getThumbnailSrc() {
        return _servletContext.getContextPath() + "/images/thumbnail.png";
    }

    @Override
    public void initialize(long groupId) throws InitializationException {
        // this is where the magic starts...
    }

    @Override
    public boolean isActive(long companyId) {
        return true;
    }

    /*private void _importJournalArticles(ServiceContext serviceContext)
            throws Exception {

        if (_log.isInfoEnabled()) {
            _log.info("Importing Journal Articles...");
        }

        _journalArticleImporter.importJournalArticles(
                _getJSONArray("journal-articles.json"),
                _speedwellDependencyResolver.getDocumentsClassLoader(),
                _speedwellDependencyResolver.getDependenciesPath() +
                        "journal_articles/",
                serviceContext.getScopeGroupId(), serviceContext.getUserId());

        if (_log.isInfoEnabled()) {
            _log.info("Journal Articles successfully imported");
        }
    }*/

    private static final Log _log = LogFactoryUtil.getLog(
            PrezzSiteInitializer.class);

    @Reference
    private JournalArticleImporter _journalArticleImporter;

    @Reference(
            target = "(osgi.web.symbolicname=com.liferay.example.site.initializer.prezz)"
    )
    private ServletContext _servletContext;
}
